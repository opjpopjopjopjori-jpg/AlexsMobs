# Logic Bugs & Fixes

## 1. Hardcoded Boolean Values

### ❌ Always true/false

**Problem**:
```java
boolean running = true;  // ← Always true!
if (running) {
    // Running animation
} else {
    // Walking animation (never executes)
}
```

**Why it's wrong**:
- Dead code (else branch never executes)
- State doesn't change based on entity
- Animation always the same

**✅ Fix**:
```java
boolean running = limbSwingAmount > 0.5F;  // ← State-based
if (running) {
    // Running animation
} else {
    // Walking animation
}
```

**Detection**:
```bash
grep -n "boolean.*= true;" Model*.java
grep -n "boolean.*= false;" Model*.java
```

**Affected files**:
- `ModelEmu.java:260`

---

## 2. Float Equality Checks

### ❌ Using == or != with floats

**Problem**:
```java
if (flyProgress == 5) {  // ← Float equality!
    // ...
}
```

**Why it's wrong**:
- Float precision issues
- 5.0F might be 4.999999F or 5.000001F
- Condition rarely true

**✅ Fix**:
```java
if (flyProgress > 4.99F) {  // ← Threshold check
    // ...
}
```

**Or**:
```java
if (Math.abs(flyProgress - 5.0F) < 0.01F) {  // ← Epsilon check
    // ...
}
```

**Detection**:
```bash
grep -n "Progress ==" Model*.java
grep -n "Progress !=" Model*.java
```

**Affected files**:
- `ModelEnderiophage.java`

---

## 3. Static Mutable Fields

### ❌ Shared state across instances

**Problem**:
```java
public class ModelMurmurNeck extends AdvancedEntityModel<LivingEntity> {
    public static boolean THIN = false;  // ← Static!
    public static boolean HIDE = false;  // ← Static!
    
    public void setAttributes(...) {
        if (THIN) {
            // ...
        }
    }
}
```

**Why it's wrong**:
- All MurmurNeck instances share same state
- Multiplayer issues (one player affects another)
- Race conditions

**✅ Fix**:
```java
public class ModelMurmurNeck extends AdvancedEntityModel<LivingEntity> {
    public boolean THIN = false;  // ← Instance field
    public boolean HIDE = false;  // ← Instance field
    // ...
}
```

**Detection**:
```bash
grep -n "public static.*boolean\|public static.*float\|public static.*int" Model*.java
# Exclude: final, ArmPose, ANIMATION
```

**Affected files**:
- `ModelMurmurNeck.java:15-16`
- `ModelKangaroo.java:38` (renderOnlyHead)

---

## 4. Raw Entity Field Access

### ❌ Using entity.field instead of interpolated progress

**Problem**:
```java
if (entity.swimProgress >= 5F) {  // ← Raw field!
    // Swimming animation
}
```

**Why it's wrong**:
- No interpolation (jerky transitions)
- Doesn't account for partialTick
- Animation snaps instead of blending

**✅ Fix**:
```java
float swimProgress = entity.prevSwimProgress + 
    (entity.swimProgress - entity.prevSwimProgress) * partialTick;
if (swimProgress >= 4.99F) {  // ← Interpolated
    // Swimming animation
}
```

**Detection**:
```bash
grep -n "entity\.[a-z]*Progress" Model*.java
# Check if it's used directly (not interpolated)
```

**Affected files**:
- `ModelLaviathan.java:193`

---

## 5. Unconditional Animation Application

### ❌ Animation always applied regardless of state

**Problem**:
```java
// Tongue animation
double tongueM = Math.min(Math.sin(ageInTicks * 0.15F), 0);
float toungeF = 12F + 12F * (float) tongueM * (feedProgress * 0.2F);
this.tongue1.rotationPointZ += toungeF;  // ← Always applied!
```

**Why it's wrong**:
- Tongue extends even when not feeding
- feedProgress = 0 → toungeF = 12F (still extends!)
- Visual bug

**✅ Fix**:
```java
// Tongue animation (only when feeding)
double tongueM = Math.min(Math.sin(ageInTicks * 0.15F), 0);
float feedFactor = feedProgress * 0.2F;
float toungeF = 12F * feedFactor + 12F * (float) tongueM * feedFactor;
this.tongue1.rotationPointZ += toungeF;  // ← 0 when not feeding
```

**Detection**:
```bash
# Look for animations that should be conditional
grep -B 5 "rotationPoint.*+=" Model*.java | grep -c "Progress"
```

**Affected files**:
- `ModelAnteater.java:216-221`

---

## 6. Overwriting Instead of Additive

### ❌ = instead of +=

**Problem**:
```java
jaw.rotateAngleX = -head.rotateAngleX;  // ← Overwrites!
```

**Why it's wrong**:
- Overwrites previous animations (breathing, basking)
- Animation conflicts
- Jaw snaps instead of blending

**✅ Fix**:
```java
jaw.rotateAngleX += -head.rotateAngleX;  // ← Additive
```

**Detection**:
```bash
grep -n "rotateAngle.*=.*rotateAngle" Model*.java
# Check if it's = (overwrite) or += (additive)
```

**Affected files**:
- `ModelCrocodile.java:283`

---

## 7. Missing State Guards

### ❌ Animations overlap without guards

**Problem**:
```java
// Sitting animation
progressRotationPrev(body, sitProgress, Maths.rad(-80), 0, 0, 10F);

// Standing animation
progressRotationPrev(body, standProgress, Maths.rad(-90), 0, 0, 10F);
```

**Why it's wrong**:
- If both sitProgress and standProgress > 0, animations conflict
- Body rotates -80° then -90° = -170° (wrong!)
- Visual glitch

**✅ Fix**:
```java
// Sitting animation
if (sitProgress > 0 && standProgress == 0) {
    progressRotationPrev(body, sitProgress, Maths.rad(-80), 0, 0, 10F);
}

// Standing animation
if (standProgress > 0) {
    progressRotationPrev(body, standProgress, Maths.rad(-90), 0, 0, 10F);
}
```

**Or use Math.max**:
```java
float dominantState = Math.max(sitProgress, standProgress);
if (dominantState == sitProgress) {
    // Sitting
} else {
    // Standing
}
```

---

## 8. Incorrect Progress Calculation

### ❌ Wrong formula

**Problem**:
```java
float runProgress = 5F * limbSwingAmount;
progressRotationPrev(neck1, runProgress, Maths.rad(120), 0, 0, 5F);
```

**Why it's wrong**:
- If limbSwingAmount = 1.0F, runProgress = 5F
- 5F / 5F = 1.0 (full animation)
- But if limbSwingAmount = 0.5F, runProgress = 2.5F
- 2.5F / 5F = 0.5 (half animation) — might be too subtle

**✅ Fix** (if needed):
```java
float runProgress = Math.min(limbSwingAmount * 2F, 1F) * 5F;
// Now 0.5F → 1.0F → 5F (full animation)
```

**Note**: This depends on desired behavior. Verify with gameplay.

---

## 9. Division by Zero Risk

### ❌ Dividing by progress that could be 0

**Problem**:
```java
float scale = 1.0F / sitProgress;  // ← Division by zero!
```

**Why it's wrong**:
- If sitProgress = 0, scale = infinity
- NaN or crash

**✅ Fix**:
```java
float scale = sitProgress > 0 ? 1.0F / sitProgress : 1.0F;
```

**Detection**:
```bash
grep -n "/ .*Progress" Model*.java
```

---

## 10. Missing Partial Tick Interpolation

### ❌ Using raw values without interpolation

**Problem**:
```java
float jostleAngle = entity.jostleAngle;  // ← No interpolation!
neck.rotateAngleY += jostleAngle * Mth.DEG_TO_RAD;
```

**Why it's wrong**:
- Jerky animation (no smooth transition)
- Doesn't account for frame timing

**✅ Fix**:
```java
float jostleAngle = entity.prevJostleAngle + 
    (entity.jostleAngle - entity.prevJostleAngle) * partialTick;
neck.rotateAngleY += jostleAngle * Mth.DEG_TO_RAD;
```

**Detection**:
```bash
grep -n "entity\.[a-z]*Angle\|entity\.[a-z]*Yaw\|entity\.[a-z]*Pitch" Model*.java
# Check if interpolated
```
