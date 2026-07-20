# Performance Issues & Fixes

## 1. Redundant resetToDefaultPose()

### ❌ Called multiple times in setupAnim

**Problem**:
```java
public void setupAnim(EntityX entity, ...) {
    this.resetToDefaultPose();  // ← First call
    animate(entity, ...);  // ← animate() also calls resetToDefaultPose()
    // ...
}

public void animate(IAnimatedEntity entity, ...) {
    this.resetToDefaultPose();  // ← Second call (redundant!)
    animator.update(entity);
    // ...
}
```

**Why it's wrong**:
- Double processing (resets all bones twice)
- Performance waste (especially with 50+ bones)
- No functional benefit

**✅ Fix**:
```java
public void setupAnim(EntityX entity, ...) {
    // NO resetToDefaultPose() here
    animate(entity, ...);  // ← animate() handles it
    // ...
}

public void animate(IAnimatedEntity entity, ...) {
    this.resetToDefaultPose();  // ← Only here
    animator.update(entity);
    // ...
}
```

**Detection**:
```bash
for f in Model*.java; do
  count=$(grep -c "resetToDefaultPose" "$f")
  [ "$count" -ge 3 ] && echo "$f: $count"
done
```

**Affected files** (7 files):
- `ModelGrizzlyBear.java`
- `ModelGorilla.java`
- `ModelRattlesnake.java`
- `ModelOrca.java`
- `ModelMoose.java`
- `ModelBison.java`
- `ModelCrocodile.java`

**Performance impact**: ~2x slower pose reset (minor but measurable)

---

## 2. Magic Numbers

### ❌ Using 57.295776F instead of Mth.DEG_TO_RAD

**Problem**:
```java
head.rotateAngleY += netHeadYaw / 57.295776F;  // ← Magic number!
```

**Why it's wrong**:
- Hard to read (what is 57.295776F?)
- Hard to maintain
- Potential typos

**✅ Fix**:
```java
head.rotateAngleY += netHeadYaw * Mth.DEG_TO_RAD;  // ← Clear intent
```

**Detection**:
```bash
grep -rn "57\.295776" Model*.java
```

**Affected files** (10+ files):
- `ModelCrow.java:167-168`
- `ModelGeladaMonkey.java:296-297`
- `ModelKomodoDragon.java:199-200`
- `ModelMoose.java:124`
- `ModelRockyRoller.java:218-219`
- `ModelSeagull.java:159-160`
- `ModelSeal.java:300`
- `ModelVoidWorm.java:48`
- `ModelVoidWormBody.java:87`
- `ModelVoidWormTail.java:60`

**Performance impact**: None (compile-time constant), but readability suffers

---

## 3. Duplicate Calculations

### ❌ Same calculation done multiple times

**Problem**:
```java
// In setupAnim
float breath = Mth.cos(ageInTicks * 0.08F);
body.rotationPointY += breath * 0.3F;

// Later in same method
float breath = Mth.cos(ageInTicks * 0.08F);  // ← Duplicate!
head.rotationPointY += breath * 0.1F;
```

**Why it's wrong**:
- Redundant calculation
- Performance waste
- Potential inconsistency if values differ

**✅ Fix**:
```java
// Calculate once
float breath = Mth.cos(ageInTicks * 0.08F);

// Use multiple times
body.rotationPointY += breath * 0.3F;
head.rotationPointY += breath * 0.1F;
```

**Detection**:
```bash
for f in Model*.java; do
  grep "float breath =" "$f" | wc -l
done
# Should be 1 per file
```

**Performance impact**: Minor (cos is fast), but code smell

---

## 4. Expensive Operations in setupAnim

### ❌ Complex calculations every frame

**Problem**:
```java
public void setupAnim(EntityX entity, ...) {
    // Expensive calculation every frame
    double distance = Math.sqrt(
        Math.pow(entity.getX() - target.getX(), 2) +
        Math.pow(entity.getY() - target.getY(), 2) +
        Math.pow(entity.getZ() - target.getZ(), 2)
    );
    // ...
}
```

**Why it's wrong**:
- setupAnim called 20+ times per second
- Expensive operations multiply
- Performance degradation

**✅ Fix**:
```java
// In entity class (server-side)
public void tick() {
    this.cachedDistance = this.distanceTo(target);  // Calculate once per tick
}

// In model class (client-side)
public void setupAnim(EntityX entity, ...) {
    float distance = entity.cachedDistance;  // Use cached value
    // ...
}
```

**Detection**:
```bash
grep -n "Math.sqrt\|Math.pow" Model*.java
# Should be rare in setupAnim
```

**Performance impact**: Significant if done every frame

---

## 5. Unnecessary Object Creation

### ❌ Creating objects in setupAnim

**Problem**:
```java
public void setupAnim(EntityX entity, ...) {
    AdvancedModelBox[] boxes = new AdvancedModelBox[]{head, body, tail};  // ← New array every frame!
    this.chainSwing(boxes, walkSpeed, walkDegree, -2, limbSwing, limbSwingAmount);
}
```

**Why it's wrong**:
- Array created 20+ times per second
- GC pressure
- Performance waste

**✅ Fix**:
```java
public class ModelX extends AdvancedEntityModel<EntityX> {
    private final AdvancedModelBox[] spineBoxes;  // ← Create once
    
    public ModelX() {
        // ...
        spineBoxes = new AdvancedModelBox[]{head, body, tail};  // ← Constructor
    }
    
    public void setupAnim(EntityX entity, ...) {
        this.chainSwing(spineBoxes, walkSpeed, walkDegree, -2, limbSwing, limbSwingAmount);  // ← Reuse
    }
}
```

**Detection**:
```bash
grep -n "new AdvancedModelBox\[\]" Model*.java
# Should be in constructor, not setupAnim
```

**Performance impact**: Minor (small arrays), but good practice

---

## 6. Redundant Conditional Checks

### ❌ Checking same condition multiple times

**Problem**:
```java
public void setupAnim(EntityX entity, ...) {
    if (entity.isInWater()) {
        // Swimming animation
    }
    
    // Later...
    if (entity.isInWater()) {  // ← Redundant check!
        // More swimming animation
    }
}
```

**Why it's wrong**:
- Redundant condition evaluation
- Code harder to follow
- Potential inconsistency

**✅ Fix**:
```java
public void setupAnim(EntityX entity, ...) {
    if (entity.isInWater()) {
        // All swimming animation in one block
    }
}
```

**Detection**:
```bash
# Manual review needed
grep -n "entity.is" Model*.java | sort | uniq -c | sort -rn
```

---

## 7. Inefficient Loop Patterns

### ❌ Nested loops with repeated calculations

**Problem**:
```java
for (int i = 0; i < bones.length; i++) {
    for (int j = 0; j < bones.length; j++) {
        float distance = calculateDistance(bones[i], bones[j]);  // ← O(n²)
        // ...
    }
}
```

**Why it's wrong**:
- O(n²) complexity
- Unnecessary for most animations
- Performance killer with many bones

**✅ Fix**:
```java
// If possible, use chain animations instead
this.chainSwing(bones, walkSpeed, walkDegree, -2, limbSwing, limbSwingAmount);
```

**Detection**:
```bash
grep -B 5 -A 5 "for.*for" Model*.java
# Look for nested loops
```

---

## 8. Excessive Method Calls

### ❌ Calling same method multiple times with same args

**Problem**:
```java
this.walk(left_leg, walkSpeed, walkDegree, false, 0F, 0F, limbSwing, limbSwingAmount);
this.walk(left_leg, walkSpeed, walkDegree, false, 0F, 0F, limbSwing, limbSwingAmount);  // ← Duplicate!
```

**Why it's wrong**:
- Double animation effect
- Performance waste
- Unnatural movement

**✅ Fix**:
```java
this.walk(left_leg, walkSpeed, walkDegree, false, 0F, 0F, limbSwing, limbSwingAmount);
// Remove duplicate
```

**Detection**:
```bash
for f in Model*.java; do
  grep "this.walk\|this.bob\|this.swing\|this.flap" "$f" | sort | uniq -d
done
```

---

## 9. Unused Imports

### ❌ Importing classes that aren't used

**Problem**:
```java
import net.minecraft.util.Mth;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
// ... but only using Mth
```

**Why it's wrong**:
- Code clutter
- Confusing (which one to use?)
- Potential conflicts

**✅ Fix**:
```java
import net.minecraft.util.Mth;
// Remove unused imports
```

**Detection**:
```bash
# IDE can detect this automatically
# Or use:
grep "^import" ModelX.java
# Manually check if each import is used
```

---

## 10. Inefficient String Operations

### ❌ String concatenation in loops

**Problem**:
```java
String debug = "";
for (AdvancedModelBox bone : bones) {
    debug += bone.getName() + ", ";  // ← O(n²) string concat
}
```

**Why it's wrong**:
- String concatenation creates new objects
- O(n²) complexity
- GC pressure

**✅ Fix**:
```java
StringBuilder debug = new StringBuilder();
for (AdvancedModelBox bone : bones) {
    debug.append(bone.getName()).append(", ");
}
```

**Note**: Rare in AlexsMobs, but important for debug logging
