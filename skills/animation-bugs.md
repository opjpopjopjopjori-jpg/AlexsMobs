# Animation Bugs & Fixes

## 1. Keyframe Stack Corruption

### ❌ Stray `endKeyframe()` after `resetKeyframe()`

**Problem**:
```java
animator.resetKeyframe(4);
animator.endKeyframe();  // ← WRONG!
```

**Why it's wrong**:
- `resetKeyframe()` already closes the keyframe
- Adding `endKeyframe()` corrupts the keyframe stack
- Causes `IllegalStateException` at runtime

**✅ Fix**:
```java
animator.resetKeyframe(4);
// NO endKeyframe() here!
```

**Detection**:
```bash
grep -n "animator.resetKeyframe" Model*.java
# Check if next line has animator.endKeyframe()
```

**Affected files**:
- `ModelGrizzlyBear.java:133,178`
- `ModelBunfungus.java:171`

---

## 2. Wrong Axis Usage

### ❌ headPitch on Z axis (roll)

**Problem**:
```java
head.rotateAngleZ += headPitch * Mth.DEG_TO_RAD;  // ← WRONG!
```

**Why it's wrong**:
- headPitch = up/down looking
- Z axis = roll (tilt sideways)
- Should be X axis (pitch = nod)

**✅ Fix**:
```java
head.rotateAngleX += headPitch * Mth.DEG_TO_RAD;
```

**Detection**:
```bash
grep -n "rotateAngleZ.*headPitch" Model*.java
grep -n "headPitch.*rotateAngleZ" Model*.java
```

**Affected files**:
- `ModelCrow.java:168`
- `ModelBaldEagle.java:276`

---

### ❌ netHeadYaw on Z axis (roll) when NOT standing

**Problem**:
```java
head.rotateAngleZ += netHeadYaw * Mth.DEG_TO_RAD;  // ← WRONG!
```

**Why it's wrong**:
- netHeadYaw = left/right looking
- Z axis = roll (tilt sideways)
- Should be Y axis (yaw = turn)

**✅ Fix**:
```java
head.rotateAngleY += netHeadYaw * Mth.DEG_TO_RAD;
```

**Exception**: When standing (body rotated 90°), Z axis becomes correct for world-space yaw.

**Detection**:
```bash
grep -n "rotateAngleZ.*netHeadYaw" Model*.java
# Verify if it's in standing state (check context)
```

**Affected files**:
- `ModelGrizzlyBear.java:224,333` (standing state - CORRECT)
- Other files (not standing - WRONG)

---

## 3. Missing resetToDefaultPose()

### ❌ No reset before keyframes

**Problem**:
```java
public void animate(IAnimatedEntity entity, ...) {
    animator.update(entity);
    animator.setAnimation(...);
    animator.startKeyframe(4);
    // Missing resetToDefaultPose()!
}
```

**Why it's wrong**:
- Previous pose accumulates
- Animations blend incorrectly
- Bones drift from default position

**✅ Fix**:
```java
public void setupAnim(EntityX entity, ...) {
    this.resetToDefaultPose();  // ← Add here
    animate(entity, ...);
}
```

**Detection**:
```bash
grep -A 5 "void setupAnim" Model*.java | grep -c "resetToDefaultPose"
# Should be 1 in every file with animations
```

---

## 4. Duplicate bob() on Same Bone

### ❌ Same bone, same parameters

**Problem**:
```java
this.bob(left_leg, walkSpeed, walkDegree, false, limbSwing, limbSwingAmount);
this.bob(left_leg, walkSpeed, walkDegree, false, limbSwing, limbSwingAmount);  // ← DUPLICATE!
```

**Why it's wrong**:
- Double animation effect
- Unnatural movement
- Performance waste

**✅ Fix**:
```java
this.bob(left_leg, walkSpeed, walkDegree, false, limbSwing, limbSwingAmount);
this.bob(right_leg, walkSpeed, walkDegree, false, limbSwing, limbSwingAmount);  // ← Different bone
```

**Detection**:
```bash
for f in Model*.java; do
  grep "this.bob(" "$f" | sort | uniq -d
done
```

**Affected files**:
- `ModelGrizzlyBear.java:268` (was left_leg, should be right_leg)

---

## 5. Progress Variable Issues

### ❌ Hardcoded progress values

**Problem**:
```java
float rideProgress = entity.isPassenger() ? 10 : 0;  // ← 10 is too high!
progressRotationPrev(body, rideProgress, 0, Maths.rad(90), 0, 10F);
```

**Why it's wrong**:
- Progress should be 0-5 range (standard)
- 10/10 = 1.0 (full animation)
- But entity progress typically goes 0-5

**✅ Fix**:
```java
float rideProgress = entity.isPassenger() ? 1F : 0;
progressRotationPrev(body, rideProgress, 0, Maths.rad(90), 0, 1F);
```

**Detection**:
```bash
grep -n "? 10 :" Model*.java
grep -n "Progress.*10" Model*.java
```

**Affected files**:
- `ModelCapuchinMonkey.java:214`

---

## 6. Missing Fields

### ❌ Undeclared `this.riding` or `this.attackTime`

**Problem**:
```java
if (this.riding) {  // ← riding not declared!
    // ...
}
```

**Why it's wrong**:
- Compilation error
- `riding` is not a field in `AdvancedEntityModel`

**✅ Fix**:
```java
public class ModelUnderminerDwarf extends AdvancedEntityModel<EntityUnderminer> {
    public boolean riding;  // ← Add this
    public float attackTime;  // ← Add this
    // ...
}
```

**Detection**:
```bash
grep -n "this.riding\|this.attackTime" Model*.java
# Check if field is declared
```

**Affected files**:
- `ModelUnderminerDwarf.java`

---

## 7. Duplicate animator.update()

### ❌ Called twice in same method

**Problem**:
```java
public void animate(IAnimatedEntity entity, ...) {
    animator.update(entity);
    animator.update(entity);  // ← DUPLICATE!
    // ...
}
```

**Why it's wrong**:
- Double processing
- Performance waste
- Potential animation glitches

**✅ Fix**:
```java
public void animate(IAnimatedEntity entity, ...) {
    animator.update(entity);  // ← Only once
    // ...
}
```

**Detection**:
```bash
for f in Model*.java; do
  count=$(grep -c "animator.update" "$f")
  [ "$count" -gt 1 ] && echo "$f: $count"
done
```

**Affected files**:
- `ModelSnowLeopard.java:124`

---

## 8. Keyframe Balance

### ✅ Rule: starts == ends

**Check**:
```bash
for f in Model*.java; do
  starts=$(grep -c "animator.startKeyframe" "$f")
  ends=$(grep -c "animator.endKeyframe" "$f")
  if [ "$starts" -ne "$ends" ]; then
    echo "$f: $starts starts, $ends ends"
  fi
done
```

**Expected**: All files should have `starts == ends`

---

## 9. State Transition Guards

### ✅ Use Math.max/min for overlapping states

**Good**:
```java
if (Math.max(standProgress, sitProgress) > 5F) {
    // Standing or sitting animation
}
```

**Good**:
```java
float legLead = Math.min(sitProgress, sitProgress * 1.25F);
float armTrail = Math.max(0, sitProgress - 0.6F);
```

**Why**: Prevents conflicting animations from overlapping

---

## 10. Breath Animation Pattern

### ✅ Single breath definition per model

**Good**:
```java
float breath = Mth.cos(ageInTicks * 0.08F);
body.rotationPointY += breath * 0.3F;
```

**Bad**:
```java
float breath = Mth.cos(ageInTicks * 0.08F);
body.rotationPointY += breath * 0.3F;
// ... later in same method ...
float breath = Mth.cos(ageInTicks * 0.1F);  // ← DUPLICATE!
```

**Detection**:
```bash
grep -c "float breath =" Model*.java
# Should be 1 per file
```
