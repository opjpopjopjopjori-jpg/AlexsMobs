# Testing Patterns

## 1. Static Analysis Checks

### ✅ Automated Validation (23 Checks)

```bash
#!/bin/bash
# run-all-checks.sh

echo "=== 1. Brace Balance ==="
for f in Model*.java; do
  open=$(grep -o '{' "$f" | wc -l)
  close=$(grep -o '}' "$f" | wc -l)
  [ "$open" -ne "$close" ] && echo "FAIL: $f"
done

echo "=== 2. Keyframe Balance ==="
for f in Model*.java; do
  starts=$(grep -c "animator.startKeyframe" "$f")
  ends=$(grep -c "animator.endKeyframe" "$f")
  [ "$starts" -ne "$ends" ] && echo "FAIL: $f (starts=$starts, ends=$ends)"
done

echo "=== 3. Duplicate Methods ==="
for f in Model*.java; do
  count=$(grep -c "Iterable<BasicModelPart> parts()" "$f")
  [ "$count" -gt 1 ] && echo "FAIL: $f (duplicate parts())"
done

echo "=== 4. Wrong Axis (headPitch on Z) ==="
grep -rn "rotateAngleZ.*headPitch" Model*.java && echo "FAIL"

echo "=== 5. Scale 0.9F in Render ==="
grep -rn "setScale(0.9F" Model*.java && echo "FAIL"

echo "=== 6. Static Mutable Fields ==="
for f in Model*.java; do
  grep "public static.*boolean\|public static.*float" "$f" | grep -v "final\|ArmPose\|ANIMATION" && echo "FAIL: $f"
done

echo "=== 7. Double animator.update ==="
for f in Model*.java; do
  count=$(grep -c "animator.update" "$f")
  [ "$count" -gt 1 ] && echo "FAIL: $f"
done

echo "=== 8. Missing Imports ==="
for f in Model*.java; do
  uses=$(grep -c "Mth\." "$f")
  imports=$(grep -c "import.*Mth" "$f")
  [ "$uses" -gt 0 ] && [ "$imports" -eq 0 ] && echo "FAIL: $f"
done

echo "=== All checks complete ==="
```

---

## 2. Stress Test Script

### ✅ Python + mcrcon

```python
#!/usr/bin/env python3
import mcrcon, re, time

HOST, PORT, PW = "127.0.0.1", 25575, "testpass"

# Read entity names
raw = open("entity_names.txt", encoding="utf-8-sig").read()
names = [m for m in (re.sub(r'[^a-z_]', '', l) for l in raw.splitlines()) if m]

# Skip non-entity types
skip = {
    "anaconda_part", "bone_serpent_part", "centipede_body",
    "murmur", "void_worm_part", "squid_grapple",
    "crimson_mosquito", "warped_mosco", "fly", "guster"
}
targets = [n for n in names if n not in skip]

# Connect
c = mcrcon.MCRcon(HOST, PW, PORT)
c.connect()

# Keep chunk loaded
c.command("forceload add 0 0")
print("forceload set")

def wave():
    for n in targets:
        try:
            c.command(f"summon alexsmobs:{n} 0 5 0")
        except Exception as e:
            print(f"EXC summon {n}: {e}")

# Summon all entities
wave()
print("wave1 done, entities near origin. ticking 60s...")

# Re-summon to keep AI active
for i in range(1, 6):
    time.sleep(12)
    print(f"re-wave {i}")
    wave()

c.command("forceload remove 0 0")
c.disconnect()
print("STRESS TEST COMPLETE")
```

---

## 3. In-Game Testing Checklist

### ✅ Manual Verification

```markdown
## Entity: GrizzlyBear

### Summon
- [ ] `/summon alexsmobs:grizzly_bear` works
- [ ] Entity spawns correctly
- [ ] No immediate crash

### Idle
- [ ] Breathing animation visible
- [ ] Head tracks player
- [ ] Ears move naturally

### Walk
- [ ] Legs move correctly
- [ ] Body bobs
- [ ] Smooth transition from idle

### Run
- [ ] Faster leg movement
- [ ] Body leans forward
- [ ] Smooth transition from walk

### Sit
- [ ] Body lowers
- [ ] Legs fold
- [ ] Smooth transition

### Stand
- [ ] Body rises
- [ ] Head adjusts axis
- [ ] Smooth transition

### Attack
- [ ] Paw swipe animation
- [ ] Keyframes play correctly
- [ ] Returns to idle

### Baby
- [ ] Head scaled correctly
- [ ] Proportions correct
- [ ] Scale resets on adult

### Freddy (Easter Egg)
- [ ] Hat visible
- [ ] Microphone visible
- [ ] Special animation plays
```

---

## 4. Build Verification

### ✅ Gradle Build Check

```bash
#!/bin/bash
# build-test.sh

echo "=== Cleaning ==="
./gradlew clean

echo "=== Building ==="
./gradlew build --no-daemon 2>&1 | tee build.log

if grep -q "BUILD SUCCESSFUL" build.log; then
    echo "✅ BUILD SUCCESS"
    
    # Check jar exists
    if [ -f build/libs/alexsmobs-*.jar ]; then
        echo "✅ JAR created"
        ls -lh build/libs/alexsmobs-*.jar
    else
        echo "❌ JAR missing"
    fi
else
    echo "❌ BUILD FAILED"
    grep "error:" build.log | head -20
fi
```

---

## 5. Regression Testing

### ✅ After Each Fix

```bash
#!/bin/bash
# regression-test.sh

echo "=== Running all checks ==="
./run-all-checks.sh

echo "=== Building ==="
./gradlew build --no-daemon

if [ $? -eq 0 ]; then
    echo "✅ Build successful"
    
    echo "=== Deploying to server ==="
    cp build/libs/alexsmobs-*.jar /path/to/server/mods/
    
    echo "=== Restarting server ==="
    /path/to/server/restart.sh
    
    echo "=== Running stress test ==="
    python3 stress-test.py
    
    echo "✅ All tests passed"
else
    echo "❌ Build failed"
    exit 1
fi
```

---

## 6. Visual Comparison

### ✅ Before/After Screenshots

```markdown
## Test: GrizzlyBear Head Tracking

### Before Fix
- Screenshot: grizzly_bear_before.png
- Issue: Head tilts sideways when looking up/down

### After Fix
- Screenshot: grizzly_bear_after.png
- Result: Head nods correctly

### Verification
- [ ] Visual improvement confirmed
- [ ] No new issues introduced
- [ ] Smooth animation
```

---

## 7. Performance Profiling

### ✅ Using Minecraft Debug Tools

```bash
# Enable debug profiler
/gametest profiler start

# Run for 10 seconds
sleep 10

# Stop and export
/gametest profiler stop

# Check results
cat debug/profiling/results.txt | grep "ModelGrizzlyBear"
```

---

## 8. Multiplayer Testing

### ✅ Cross-Entity Validation

```markdown
## Test: MurmurNeck Static Fields

### Setup
- Player A: Spawns Murmur
- Player B: Spawns Murmur (different type)

### Expected
- Player A's Murmur: THIN = false
- Player B's Murmur: THIN = true

### Before Fix
- Both Murrurs: THIN = true (shared state) ❌

### After Fix
- Player A's Murmur: THIN = false ✅
- Player B's Murmur: THIN = true ✅
```

---

## 9. Edge Case Testing

### ✅ Boundary Conditions

```markdown
## Test: Progress Variable Boundaries

### Test Cases
1. sitProgress = 0 (not sitting)
2. sitProgress = 2.5 (halfway)
3. sitProgress = 5 (fully sitting)
4. sitProgress = 5.1 (overflow)

### Expected
1. Standing animation
2. Blended animation
3. Sitting animation
4. Sitting animation (clamped)

### Verification
- [ ] No crashes
- [ ] Smooth transitions
- [ ] Correct animations
```

---

## 10. Automated CI/CD

### ✅ GitHub Actions

```yaml
# .github/workflows/test.yml
name: Animation Tests
on: [push, pull_request]

jobs:
  test:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4
      
      - name: Set up JDK 17
        uses: actions/setup-java@v4
        with:
          java-version: '17'
          distribution: 'temurin'
      
      - name: Run static checks
        run: ./run-all-checks.sh
      
      - name: Build
        run: ./gradlew build --no-daemon
      
      - name: Upload build
        uses: actions/upload-artifact@v4
        with:
          name: mod-jar
          path: build/libs/*.jar
```
