# Common Pitfalls

## 1. Assuming Static Analysis is Enough

### ❌ Only checking code, not testing in-game

**Problem**:
- Static analysis finds syntax errors
- But misses visual/animation bugs
- Axis errors only visible in-game
- Progress values need gameplay testing

**✅ Solution**:
- Static analysis + in-game testing
- Use stress test scripts
- Verify each animation state
- Check transitions

---

## 2. Forgetting Keyframe Balance

### ❌ Not verifying starts == ends

**Problem**:
```java
animator.startKeyframe(5);
animator.rotate(head, Maths.rad(30), 0, 0);
animator.endKeyframe();
animator.startKeyframe(3);
animator.move(head, 0, -2, 0);
// Missing endKeyframe()!
```

**✅ Solution**:
```bash
# Always check after editing
for f in Model*.java; do
  starts=$(grep -c "animator.startKeyframe" "$f")
  ends=$(grep -c "animator.endKeyframe" "$f")
  [ "$starts" -ne "$ends" ] && echo "MISMATCH: $f"
done
```

---

## 3. Confusing Axis in Standing State

### ❌ Using Y axis when body is rotated 90°

**Problem**:
```java
// Standing state (body.rotateAngleX = -90°)
head.rotateAngleY += netHeadYaw * Mth.DEG_TO_RAD;  // WRONG!
// Y axis now points forward, not up
```

**✅ Solution**:
```java
if (standProgress > 5F) {
    head.rotateAngleZ += netHeadYaw * Mth.DEG_TO_RAD;  // Z becomes yaw
} else {
    head.rotateAngleY += netHeadYaw * Mth.DEG_TO_RAD;
}
```

---

## 4. Not Interpolating Progress

### ❌ Using raw entity fields

**Problem**:
```java
if (entity.sitProgress > 0) {  // Jerky transition
    // ...
}
```

**✅ Solution**:
```java
float sitProgress = entity.prevSitProgress + 
    (entity.sitProgress - entity.prevSitProgress) * partialTick;
if (sitProgress > 0) {  // Smooth transition
    // ...
}
```

---

## 5. Overwriting Instead of Additive

### ❌ Using = instead of +=

**Problem**:
```java
jaw.rotateAngleX = Maths.rad(30);  // Overwrites breathing
```

**✅ Solution**:
```java
jaw.rotateAngleX += Maths.rad(30);  // Additive
```

---

## 6. Forgetting Scale Reset

### ❌ Baby scale persists to adults

**Problem**:
```java
if (this.young) {
    head.setScale(1.5F, 1.5F, 1.5F);
    // ... render ...
    // Missing reset!
}
```

**✅ Solution**:
```java
if (this.young) {
    head.setScale(1.5F, 1.5F, 1.5F);
    // ... render ...
    head.setScale(1F, 1F, 1F);  // Reset
}
```

---

## 7. Duplicate Method Calls

### ❌ Same animation twice

**Problem**:
```java
this.bob(left_leg, walkSpeed, walkDegree, false, limbSwing, limbSwingAmount);
this.bob(left_leg, walkSpeed, walkDegree, false, limbSwing, limbSwingAmount);  // Duplicate!
```

**✅ Solution**:
```bash
# Check for duplicates
for f in Model*.java; do
  grep "this.bob\|this.walk" "$f" | sort | uniq -d
done
```

---

## 8. Hardcoded Values

### ❌ Using magic numbers

**Problem**:
```java
if (progress > 5) {  // What is 5?
    // ...
}
```

**✅ Solution**:
```java
final float MAX_PROGRESS = 5F;
if (progress > MAX_PROGRESS) {
    // ...
}
```

---

## 9. Not Testing All States

### ❌ Only testing idle/walk

**Problem**:
- Tested: idle, walk
- Not tested: sit, stand, swim, attack, sleep
- Bugs found later

**✅ Solution**:
```python
# Stress test all entities
entities = ["grizzly_bear", "crocodile", "cockroach", ...]
for entity in entities:
    summon(entity)
    # Test all states
    command(f"data merge entity @e[type=alexsmobs:{entity},limit=1] {{Sitting:1b}}")
    command(f"data merge entity @e[type=alexsmobs:{entity},limit=1] {{Standing:1b}}")
```

---

## 10. Ignoring Performance

### ❌ Expensive operations in setupAnim

**Problem**:
```java
public void setupAnim(...) {
    // Called 20+ times per second
    for (int i = 0; i < 100; i++) {
        // Expensive calculation
    }
}
```

**✅ Solution**:
- Cache calculations
- Use chain animations
- Avoid object creation
- Profile with debug tools
