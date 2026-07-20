# Rendering Bugs & Fixes

## 1. Scale Not Reset in renderToBuffer()

### ❌ Baby head scale persists

**Problem**:
```java
public void renderToBuffer(PoseStack matrixStackIn, ...) {
    if (this.young) {
        float f = 1.75F;
        head.setScale(f, f, f);
        head.setShouldScaleChildren(true);
        matrixStackIn.pushPose();
        matrixStackIn.scale(0.35F, 0.35F, 0.35F);
        parts().forEach(...);
        matrixStackIn.popPose();
        // Missing: head.setScale(1F, 1F, 1F);
    } else {
        // ...
    }
}
```

**Why it's wrong**:
- Scale persists to next render call
- Adult entities get baby head size
- Visual glitch

**✅ Fix**:
```java
public void renderToBuffer(PoseStack matrixStackIn, ...) {
    if (this.young) {
        float f = 1.75F;
        head.setScale(f, f, f);
        head.setShouldScaleChildren(true);
        matrixStackIn.pushPose();
        matrixStackIn.scale(0.35F, 0.35F, 0.35F);
        parts().forEach(...);
        matrixStackIn.popPose();
        head.setScale(1F, 1F, 1F);  // ← Reset!
    } else {
        // ...
    }
}
```

**Detection**:
```bash
for f in Model*.java; do
  awk '/renderToBuffer/,/^    }/' "$f" | grep -c "setScale"
done
# Check if reset exists
```

**Affected files**: 20+ files (CachalotWhale, Caiman, CapuchinMonkey, Crocodile, Emu, Froststalker, Gorilla, GrizzlyBear, Kangaroo, KomodoDragon, Laviathan, LeafcutterAntQueen, ManedWolf, Raccoon, RainFrog, Rattlesnake, Seal, SnowLeopard, Tiger, etc.)

---

## 2. Wrong Head Scale Value

### ❌ 0.9F instead of 1.0F

**Problem**:
```java
public void renderToBuffer(PoseStack matrixStackIn, ...) {
    if (this.young) {
        float f = 1.75F;
        head.setScale(f, f, f);
        // ...
        head.setScale(1F, 1F, 1F);
    } else {
        matrixStackIn.pushPose();
        parts().forEach(...);
        matrixStackIn.popPose();
        head.setScale(0.9F, 0.9F, 0.9F);  // ← WRONG!
    }
}
```

**Why it's wrong**:
- Adult head is 90% size
- Permanent scale reduction
- Visual inconsistency

**✅ Fix**:
```java
} else {
    matrixStackIn.pushPose();
    parts().forEach(...);
    matrixStackIn.popPose();
    head.setScale(1.0F, 1.0F, 1.0F);  // ← Correct!
}
```

**Detection**:
```bash
grep -n "setScale(0.9F" Model*.java
```

**Affected files**:
- `ModelCrow.java:184,186`
- `ModelSeagull.java:183,185`
- `ModelTerrapin.java`

---

## 3. Baby Head Scale Not Applied

### ❌ Scale declared but not used

**Problem**:
```java
public void renderToBuffer(PoseStack matrixStackIn, ...) {
    if (this.young) {
        float f = 1.75F;  // ← Declared but not used!
        matrixStackIn.pushPose();
        matrixStackIn.scale(0.65F, 0.65F, 0.65F);
        parts().forEach(...);
        matrixStackIn.popPose();
    } else {
        // ...
    }
}
```

**Why it's wrong**:
- Baby head doesn't get scaled
- Inconsistent with other models
- Visual bug

**✅ Fix**:
```java
if (this.young) {
    float f = 1.75F;
    head.setScale(f, f, f);  // ← Apply scale
    head.setShouldScaleChildren(true);
    matrixStackIn.pushPose();
    matrixStackIn.scale(0.65F, 0.65F, 0.65F);
    parts().forEach(...);
    matrixStackIn.popPose();
    head.setScale(1F, 1F, 1F);  // ← Reset
}
```

**Detection**:
```bash
grep -B 2 -A 10 "if (this.young)" Model*.java | grep -A 5 "float f ="
# Check if f is used
```

**Affected files**:
- `ModelJerboa.java`

---

## 4. setShouldScaleChildren Not Set

### ❌ Children don't inherit scale

**Problem**:
```java
if (this.young) {
    float f = 1.75F;
    head.setScale(f, f, f);
    // Missing: head.setShouldScaleChildren(true);
    // ...
}
```

**Why it's wrong**:
- Child bones (ears, horns) don't scale
- Inconsistent proportions
- Visual glitch

**✅ Fix**:
```java
if (this.young) {
    float f = 1.75F;
    head.setScale(f, f, f);
    head.setShouldScaleChildren(true);  // ← Add this
    // ...
}
```

**Detection**:
```bash
grep -B 2 -A 5 "head.setScale" Model*.java | grep -c "setShouldScaleChildren"
# Should be 1 for each baby scale
```

---

## 5. Multiple Scales Without Reset

### ❌ Multiple bones scaled, not all reset

**Problem**:
```java
if (this.young) {
    head.setScale(1.5F, 1.5F, 1.5F);
    horn_left.setScale(0.5F, 0.5F, 0.5F);
    horn_right.setScale(0.5F, 0.5F, 0.5F);
    // ...
    head.setScale(1F, 1F, 1F);
    // Missing: horn_left and horn_right reset!
}
```

**Why it's wrong**:
- Horns stay small on adults
- Persistent scale glitch

**✅ Fix**:
```java
if (this.young) {
    head.setScale(1.5F, 1.5F, 1.5F);
    horn_left.setScale(0.5F, 0.5F, 0.5F);
    horn_right.setScale(0.5F, 0.5F, 0.5F);
    // ...
    head.setScale(1F, 1F, 1F);
    horn_left.setScale(1F, 1F, 1F);  // ← Reset all
    horn_right.setScale(1F, 1F, 1F);
}
```

**Detection**:
```bash
awk '/if \(this\.young\)/,/^    }/' Model*.java | grep "setScale"
# Count scales vs resets
```

---

## 6. Render Order Issues

### ❌ Transparent parts rendered before opaque

**Problem**:
```java
public void renderToBuffer(PoseStack matrixStackIn, ...) {
    // Transparent parts first
    glass.render(matrixStackIn, buffer, packedLight, packedOverlay);
    // Opaque parts second
    body.render(matrixStackIn, buffer, packedLight, packedOverlay);
}
```

**Why it's wrong**:
- Transparency blending issues
- Visual artifacts

**✅ Fix**:
```java
public void renderToBuffer(PoseStack matrixStackIn, ...) {
    // Opaque parts first
    body.render(matrixStackIn, buffer, packedLight, packedOverlay);
    // Transparent parts second
    glass.render(matrixStackIn, buffer, packedLight, packedOverlay);
}
```

**Note**: This is rare in AlexsMobs, but important for custom models with transparency.

---

## 7. Missing parts() Override

### ❌ Model doesn't override parts()

**Problem**:
```java
public class ModelX extends AdvancedEntityModel<EntityX> {
    // Missing: @Override public Iterable<BasicModelPart> parts()
}
```

**Why it's wrong**:
- Compilation error
- Model can't render

**✅ Fix**:
```java
@Override
public Iterable<BasicModelPart> parts() {
    return ImmutableList.of(root);
}
```

**Detection**:
```bash
for f in Model*.java; do
  grep -q "Iterable<BasicModelPart> parts()" "$f" || echo "$f: missing"
done
```

---

## 8. getAllParts() Incomplete

### ❌ Not all bones included

**Problem**:
```java
@Override
public Iterable<AdvancedModelBox> getAllParts() {
    return ImmutableList.of(root, body, head);
    // Missing: legs, arms, tail, etc.
}
```

**Why it's wrong**:
- resetToDefaultPose() doesn't reset all bones
- Animation drift
- Pose accumulation

**✅ Fix**:
```java
@Override
public Iterable<AdvancedModelBox> getAllParts() {
    return ImmutableList.of(
        root, body, head,
        left_leg, right_leg,
        left_arm, right_arm,
        tail, tail2, tail3
        // Include ALL bones
    );
}
```

**Detection**:
```bash
# Count bones in constructor vs getAllParts
grep -c "new AdvancedModelBox" ModelX.java
grep "getAllParts" -A 20 ModelX.java | grep -c ","
# Should match
```
