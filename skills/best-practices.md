# Best Practices for AlexsMobs Animation

## 1. Keyframe Patterns

### ✅ Standard Animation Structure

```java
public void animate(IAnimatedEntity entity, float f, float f1, float f2, float f3, float f4) {
    this.resetToDefaultPose();
    animator.update(entity);
    
    // Animation 1
    animator.setAnimation(EntityX.ANIMATION_ATTACK);
    animator.startKeyframe(5);
    animator.rotate(head, Maths.rad(30), 0, 0);
    animator.rotate(jaw, Maths.rad(60), 0, 0);
    animator.endKeyframe();
    animator.resetKeyframe(5);
    // NO endKeyframe() after resetKeyframe()!
    
    // Animation 2
    animator.setAnimation(EntityX.ANIMATION_EAT);
    animator.startKeyframe(3);
    animator.move(head, 0, -2, 0);
    animator.endKeyframe();
    animator.startKeyframe(3);
    animator.move(head, 0, -4, 0);
    animator.endKeyframe();
    animator.resetKeyframe(3);
}
```

**Rules**:
- ✅ `startKeyframe()` → `endKeyframe()` (matched pairs)
- ✅ `resetKeyframe()` (no endKeyframe after)
- ✅ Total: starts == ends
- ✅ resetToDefaultPose() at start

---

## 2. Axis Usage

### ✅ Correct Axis Mapping

```java
// Head tracking
head.rotateAngleY += netHeadYaw * Mth.DEG_TO_RAD;  // Y = yaw (left/right)
head.rotateAngleX += headPitch * Mth.DEG_TO_RAD;   // X = pitch (up/down)

// Exception: Standing state (body rotated 90°)
if (standProgress > 5F) {
    head.rotateAngleZ += netHeadYaw * Mth.DEG_TO_RAD;  // Z becomes yaw in world space
} else {
    head.rotateAngleY += netHeadYaw * Mth.DEG_TO_RAD;
}
```

**Rules**:
- ✅ X = pitch (nod up/down)
- ✅ Y = yaw (turn left/right)
- ✅ Z = roll (tilt sideways)
- ⚠️ Exception: Standing state changes axis mapping

---

## 3. State Transitions

### ✅ Smooth Blending with Progress Variables

```java
public void setupAnim(EntityX entity, ...) {
    // Interpolate progress
    float sitProgress = entity.prevSitProgress + 
        (entity.sitProgress - entity.prevSitProgress) * partialTick;
    float standProgress = entity.prevStandProgress + 
        (entity.standProgress - entity.prevStandProgress) * partialTick;
    
    // Use Math.max/min for overlapping states
    if (Math.max(sitProgress, standProgress) > 5F) {
        // Dominant state animation
    }
    
    // Apply animations
    progressRotationPrev(body, sitProgress, Maths.rad(-80), 0, 0, 10F);
    progressRotationPrev(body, standProgress, Maths.rad(-90), 0, 0, 10F);
}
```

**Rules**:
- ✅ Always interpolate with partialTick
- ✅ Use Math.max/min for overlapping states
- ✅ Progress range: 0-5 (standard)
- ✅ Denominator in progressRotationPrev = max progress

---

## 4. Progress Variables

### ✅ Standard Progress Pattern

```java
// In entity class
public float sitProgress = 0F;
public float prevSitProgress = 0F;

public void tick() {
    this.prevSitProgress = this.sitProgress;
    if (this.isSitting()) {
        if (this.sitProgress < 5F) this.sitProgress++;
    } else {
        if (this.sitProgress > 0F) this.sitProgress--;
    }
}

// In model class
float sitProgress = entity.prevSitProgress + 
    (entity.sitProgress - entity.prevSitProgress) * partialTick;
progressRotationPrev(body, sitProgress, Maths.rad(-80), 0, 0, 5F);
```

**Rules**:
- ✅ Progress range: 0-5
- ✅ Always interpolate in model
- ✅ Use prevX and X fields
- ✅ Increment/decrement in entity tick

---

## 5. Breath Animation

### ✅ Single Breath Definition

```java
public void setupAnim(EntityX entity, ...) {
    // Calculate once
    float breath = Mth.cos(ageInTicks * 0.08F);
    
    // Apply to multiple bones
    body.rotationPointY += breath * 0.3F;
    body.setScale(1.0F + breath * 0.02F, 1.0F, 1.0F);
    head.rotationPointY += breath * 0.1F;
}
```

**Rules**:
- ✅ Single breath calculation per method
- ✅ Reuse for multiple bones
- ✅ Small values (0.02-0.3) for subtle effect
- ✅ Use cos for smooth oscillation

---

## 6. Chain Animations

### ✅ Using chainSwing/chainWave

```java
// Define bone array once (in constructor)
private final AdvancedModelBox[] tailBoxes;

public ModelX() {
    // ...
    tailBoxes = new AdvancedModelBox[]{tail1, tail2, tail3, tail4};
}

// Use in setupAnim
public void setupAnim(EntityX entity, ...) {
    this.chainSwing(tailBoxes, walkSpeed, walkDegree * 0.5F, -2, limbSwing, limbSwingAmount);
    this.chainWave(spineBoxes, idleSpeed, idleDegree * 0.3F, -1.5F, ageInTicks, 1.0F);
}
```

**Rules**:
- ✅ Create arrays in constructor (not setupAnim)
- ✅ Negative offset for trailing motion
- ✅ Reduce degree for each segment
- ✅ chainSwing = Y rotation, chainWave = X rotation

---

## 7. Face Tracking

### ✅ Using faceTarget

```java
public void setupAnim(EntityX entity, ...) {
    // Simple face tracking
    this.faceTarget(netHeadYaw, headPitch, 1.0F, head);
    
    // Multi-bone tracking
    this.faceTarget(netHeadYaw, headPitch, 2.0F, neck, head);
    
    // With multiplier
    this.faceTarget(netHeadYaw, headPitch, 1.5F, head);  // 1.5x rotation
}
```

**Rules**:
- ✅ Call after all other animations
- ✅ Multiplier controls rotation amount
- ✅ Multiple bones = distributed rotation
- ✅ Only for entities with heads

---

## 8. Render Method

### ✅ Proper Scale Reset

```java
@Override
public void renderToBuffer(PoseStack matrixStackIn, ...) {
    if (this.young) {
        // Apply baby scale
        float f = 1.75F;
        head.setScale(f, f, f);
        head.setShouldScaleChildren(true);
        
        matrixStackIn.pushPose();
        matrixStackIn.scale(0.35F, 0.35F, 0.35F);
        matrixStackIn.translate(0.0D, 2.75D, 0.125D);
        parts().forEach(p -> p.render(matrixStackIn, buffer, packedLight, packedOverlay, red, green, blue, alpha));
        matrixStackIn.popPose();
        
        // Reset scale
        head.setScale(1F, 1F, 1F);
    } else {
        matrixStackIn.pushPose();
        parts().forEach(p -> p.render(matrixStackIn, buffer, packedLight, packedOverlay, red, green, blue, alpha));
        matrixStackIn.popPose();
    }
}
```

**Rules**:
- ✅ Always reset scale after young branch
- ✅ Use pushPose/popPose for transformations
- ✅ Reset all scaled bones
- ✅ setShouldScaleChildren for child bones

---

## 9. getAllParts

### ✅ Include ALL Bones

```java
@Override
public Iterable<AdvancedModelBox> getAllParts() {
    return ImmutableList.of(
        root,
        body, midbody,
        head, snout, left_ear, right_ear,
        left_arm, right_arm,
        left_leg, right_leg,
        hat, microphone  // Include ALL bones
    );
}
```

**Rules**:
- ✅ Include every bone created in constructor
- ✅ Order doesn't matter
- ✅ Used by resetToDefaultPose()
- ✅ Missing bones = animation drift

---

## 10. Animation Method Calls

### ✅ Correct Usage

```java
// Walk animation (limb swing based)
this.walk(bone, speed, degree, invert, offset, weight, limbSwing, limbSwingAmount);

// Bob animation (vertical movement)
this.bob(bone, speed, degree, invert, limbSwing, limbSwingAmount);

// Swing animation (rotation)
this.swing(bone, speed, degree, invert, offset, weight, limbSwing, limbSwingAmount);

// Flap animation (wing-like)
this.flap(bone, speed, degree, invert, offset, weight, limbSwing, limbSwingAmount);

// Idle animations (ageInTicks based)
this.walk(bone, speed, degree, invert, offset, weight, ageInTicks, 1.0F);
this.bob(bone, speed, degree, invert, ageInTicks, 1.0F);
```

**Rules**:
- ✅ Movement: use limbSwing, limbSwingAmount
- ✅ Idle: use ageInTicks, 1.0F
- ✅ invert: true for opposite phase
- ✅ offset: phase shift (0-3)
- ✅ weight: animation strength (0-1)
