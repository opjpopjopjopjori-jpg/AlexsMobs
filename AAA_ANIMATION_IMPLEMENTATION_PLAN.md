# AAA_ANIMATION_IMPLEMENTATION_PLAN.md
## Priority A — Bone-Verified Implementation Roadmap
**Phase 3: Planning (READ-ONLY)** | **Date**: 2026-07-20

---

## CRITICAL RULES

1. **ONLY animate bones that EXIST in the model.** Zero invented bones. Zero invented body parts.
2. **NEVER modify the model constructor.** No new `addBox()`, no new `addChild()`, no new bones.
3. **NEVER modify `getAllParts()` or `parts()`.**
4. **`resetToDefaultPose()` MUST be the first call in every `setupAnim()`.**
5. **Preserve ALL existing `ModelAnimator` sequences and all progress transitions.**
6. **No two creatures may share animation formulas, motion curves, amplitudes, frequencies, offsets, or timing.**
7. **Push to model ceiling — not to a subjective score, but to what the bones physically allow.**

---

## A-1: COCKROACH — Tripedal Escape Artist

### Biological Identity
Cockroaches are thigmotactic (wall-following), negatively phototactic (light-avoiding), and have the fastest escape response in the insect world. They use alternating tripedal gait at slow speeds, switching to quadrupedal gallop at high speeds. Wings (tegmina + hindwings) deploy in a two-stage sequence: wing covers lift → hindwings unfold. Antennae are primary sensory organs — constantly sweeping, tapping surfaces, following walls. Cerci (rear sensory appendages) detect air pressure changes and trigger instant escape. They can survive headless for weeks.

### Movement Identity vs Similar Creatures
- **vs LeafcutterAnt**: Ant marches in straight lines following pheromone trails. Cockroach scuttles erratically, changes direction unpredictably, hugs walls. Ant carries leaf overhead; cockroach has nothing.
- **vs TarantulaHawk**: Wasp is an aerial hunter with search-pause walk. Cockroach is a ground survivor with continuous scuttle and burst escape.

### Bone Inventory (VERIFIED from ModelCockroach.java)
```
root
├── abdomen          ← Main body segment. Has setScale() for breathing.
│   ├── left_leg_front   ← Front-left leg (shorter stride IRL)
│   ├── right_leg_front  ← Front-right leg
│   ├── left_leg_mid     ← Mid-left leg
│   ├── right_leg_mid    ← Mid-right leg
│   ├── left_leg_back    ← Rear-left leg (longer stride IRL)
│   ├── right_leg_back   ← Rear-right leg
│   ├── left_wing        ← Wing cover + hindwing (single flat bone)
│   ├── right_wing       ← Wing cover + hindwing (single flat bone)
│   └── neck             ← Neck segment
│       └── head         ← Head
│           ├── left_antenna   ← Left antenna (sensory)
│           └── right_antenna  ← Right antenna (sensory)
```

All 14 bones exist. No separate wing-cover bone — wings are a single bone each. No cerci bones. No leg joint bones (femur/tibia).

### Planned Animations — Bone-Verified

| Animation | Bones Used | Feasibility |
|---|---|---|
| **Visible breathing** | `abdomen.setScale()` | ✅ Already present (bi-axial scale) |
| **Tripedal walk** (keep) | All 6 leg bones via `swing()` | ✅ Already correct pattern |
| **Rear-leg power differentiation** | `left_leg_back`, `right_leg_back` with higher degree | ✅ Just change degree parameter |
| **Dash burst gait** | All 6 legs with speed-dependent degree scaling | ✅ `limbSwingAmount` can modulate speed |
| **Antenna independent probing** (keep) | Both antennae with different freqs | ✅ Already present with unique offsets |
| **Wing flight — fix axis** | `left_wing`, `right_wing` — change from `swing()` to `flap()` | ✅ `flap()` uses Z-axis (vertical), correct for insect wings |
| **Wing tegmina-covers lift** | Wings angled up 30° then flap — simulate two-stage deploy | ✅ Single wing bone can rotate up then flap |
| **Head stabilization** | `head` via `faceTarget()` | ✅ Already present |
| **Neck articulation** | `neck` micro-rotate during walk | ✅ Already present |
| **Body roll during walk** | `abdomen.rotationPointX` lateral sway | ✅ Already present |

### What CANNOT Be Solved
- ❌ **Wall-following antenna bias**: Requires entity knowledge of wall proximity (no such data in `setupAnim()` parameters). The antennae can sweep independently but can't "know" where walls are.
- ❌ **Cercal escape startle**: No cerci bones exist. Escape dash is the best approximation.
- ❌ **Individual leg joint articulation**: Legs are single flat bones (7x0 pixels each). No femur/tibia joints.
- ❌ **Separate wing covers + hindwings**: Both are a single `left_wing`/`right_wing` bone. Two-stage deploy must be simulated via sequential rotation.
- ❌ **Headless survival animation**: Already handled via `showModel = false`. No animation needed for missing head.

### Implementation Plan
1. **Fix wing flight axis**: Change `this.swing(left_wing, ...)` to `this.flap(left_wing, ...)` for vertical insect wing beats. Add wing-cover lift angle before flap starts.
2. **Add dash gait**: When `limbSwingAmount > 0.6F`, scale leg degrees up 1.8x and increase walk speed 2.5x. Body lowers (abdomen descends) during dash.
3. **Differentiate leg amplitudes**: Front legs: `walkDegree * 0.7` (shorter steps). Mid legs: `walkDegree * 0.9`. Rear legs: `walkDegree * 1.2` (power stroke).
4. **Increase antenna activity amplitude**: Change from 0.22F/0.25F to 0.35F degree for more visible sweeping.
5. **Add abdomen tip drag**: `abdomen.rotateAngleX += Mth.sin(limbSwing * walkSpeed) * 0.03F` for subtle rear drag during walk.

### Realistic Maximum: **6.5/10** (up from 4/10)
Ceiling limited by: flat leg bones (no joints), single wing bone (no two-stage deploy possible), no cerci, no wall-following data. The 14 bones are used near their limit after these changes.

---

## A-2: TERRAPIN — Shell-Rock Paddler

### Biological Identity
Terrapins are aquatic turtles with webbed feet. They swim using front limbs as PRIMARY paddles (70% propulsion) and rear limbs as rudders/stabilizers (30%). On land, they walk with a distinctive shell-rocking gait — as each diagonal pair lifts, the shell rocks to the opposite side. They extend their neck to graze on aquatic plants. Their shell is fused to their spine — body and shell are mechanically linked.

### Movement Identity vs Similar Creatures
- **vs AlligatorSnappingTurtle**: Snapper is an ambush predator — stationary, explosive snap. Terrapin is a peaceful grazer — continuous swimming, gentle walking.
- **vs Sea Turtle (not in mod)**: Sea turtles use front flippers for flying underwater. Terrapins paddle with webbed feet.

### Bone Inventory (VERIFIED from ModelTerrapin.java)
```
root
├── body              ← Core body. Separate from shell!
│   ├── head          ← Head extends/retracts
│   ├── tail          ← Short tail
│   ├── shell         ← SHELL IS A SEPARATE BONE from body! Can rock independently.
│   ├── left_arm      ← Front-left limb
│   │   └── left_hand ← Front-left paddle
│   ├── right_arm     ← Front-right limb
│   │   └── right_hand← Front-right paddle
│   ├── left_leg      ← Rear-left limb
│   │   └── left_foot ← Rear-left foot
│   └── right_leg     ← Rear-right limb
│       └── right_foot← Rear-right foot
```

13 bones. SHELL IS SEPARATE from body — this is the KEY finding. Body and shell can be animated independently.

### Planned Animations — Bone-Verified

| Animation | Bones Used | Feasibility |
|---|---|---|
| **Visible breathing** | `body.setScale()` + `shell.setScale()` | ✅ Shell and body can both scale |
| **Shell-rock during walk** | `shell.rotateAngleZ` counter-rotates from `body.rotationPointX` sway | ✅ Shell is independent bone |
| **Diagonal gait** (fix) | Arms and legs with explicit diagonal pairing: L-arm+R-leg simultaneous, R-arm+L-leg simultaneous | ✅ 4 limbs, can pair diagonally |
| **Front-limb primary swim** | `left_arm/hand` with `walkDegree*1.4`; `left_leg/foot` with `walkDegree*0.6` | ✅ Just parameter differentiation |
| **Grazing neck extension** | `head.rotationPointZ -= 2F` during graze state + `head.rotateAngleX` downward | ✅ Head is separate bone, can extend |
| **Basking posture** | Limbs splay outward via progressRotation | ✅ Can add basking state |
| **Head bobbing during walk** | `head.rotationPointY` follows body step rhythm | ✅ Head is child of body, can offset |
| **Tail idle sway** (keep) | `tail` swing at idle | ✅ Already present |

### What CANNOT Be Solved
- ❌ **Neck vertebrae articulation**: Head is a single bone attached directly to body. No neck segments to articulate S-curve neck extension.
- ❌ **Individual toe articulation**: Feet are flat single bones. No toe bones.
- ❌ **Shell scute detail movement**: Shell is a single box. No individual scute bones.
- ❌ **Mouth/jaw articulation**: No jaw bone exists. Grazing is neck+head positioning only.

### Implementation Plan
1. **Add visible breathing**: `body.setScale(1+breath*0.012, 1, 1+breath*0.012)` + `shell.setScale(1+breath*0.01, 1+breath*0.01, 1+breath*0.01)`. Keep existing `rotationPointY`.
2. **Implement diagonal walk gait**: Left arm + right leg swing together (same phase), right arm + left leg swing together (opposite phase). Replace current uniform walk calls.
3. **Differentiate swim limbs**: Front arms get `walkDegree*1.3, walkSpeed*1.2` for primary paddle stroke. Rear legs get `walkDegree*0.6` for rudder action. Add hand/foot counter-rotation to stay flat against water.
4. **Add shell-rock during walk**: `shell.rotateAngleZ += Mth.sin(limbSwing * walkSpeed) * 0.08F * limbSwingAmount`. Shell rocks OPPOSITE to body sway direction.
5. **Add grazing idle**: When stationary and `swimProgress <= 0`, head extends forward-downward periodically: `head.rotateAngleX += Mth.sin(ageInTicks * 0.07F) * 0.15F` and `head.rotationPointZ += Mth.sin(ageInTicks * 0.07F + 0.5F) * 1.5F` (only positive extension).

### Realistic Maximum: **5/10** (up from 3/10)
Ceiling limited by: no neck vertebrae (head is a block on body), flat paddle bones (no webbing articulation), no jaw. The shell-body separation is the model's best feature and will be fully utilized.

---

## A-3: SUGAR GLIDER — Membrane Deployer

### Biological Identity
Sugar gliders are arboreal marsupials with a patagium — a gliding membrane stretching from wrist to ankle. They climb tree trunks vertically using all four limbs with sharp claws. When gliding, they spread all four limbs to stretch the membrane taut, then steer by adjusting limb angles and tail position. The tail is a critical counterbalance — it curls and shifts to control pitch and roll during glide. They land by stalling (angling body up) and absorbing impact with all four limbs. Big eyes provide night vision.

### Movement Identity vs Similar Creatures
- **vs Flying Squirrel (not in mod)**: Sugar gliders are marsupials, not rodents. They climb more and glide less distance.
- **vs Jerboa/Kangaroo**: Both are ground hoppers. Sugar glider is an arboreal glider — completely different locomotion.

### Bone Inventory (VERIFIED from ModelSugarGlider.java)
```
root
├── body              ← Main body. Rotation drives glide pitch.
│   ├── leftArm       ← Left front limb (patagium front anchor)
│   ├── rightArm      ← Right front limb
│   ├── leftLeg       ← Left rear limb (patagium rear anchor)
│   ├── rightLeg      ← Right rear limb
│   ├── tail          ← Long tail (counterbalance). Can curl.
│   └── head          ← Head with big eyes
│       ├── leftEar   ← Left ear
│       └── rightEar  ← Right ear
```

10 bones. NO membrane bone. The patagium is a TEXTURE that stretches between limbs. Animation can only position the limbs; the texture renderer handles the membrane visually.

### Planned Animations — Bone-Verified

| Animation | Bones Used | Feasibility |
|---|---|---|
| **Visible breathing** | `body.setScale()` | ✅ Add setScale call |
| **Membrane deploy sequence** | `leftArm/rightArm` rotateAngleZ outward → `leftLeg/rightLeg` rotateAngleZ outward — sequential NOT simultaneous | ✅ Limbs can rotate outward in sequence |
| **Active tail counterbalance** | `tail` with `rotateAngleX` curl AND `rotateAngleY` sway — dual-axis | ✅ Tail is single bone but can rotate on both axes |
| **Vertical climb state** | Body rotated 90° (`rotateAngleX`), limbs reach forward gripping | ✅ Can add climb progress state |
| **Ground walk — differentiate front/rear** | Front arms: shorter stride `walkDegree*0.7`. Rear legs: longer stride `walkDegree*1.3` | ✅ Just parameter change |
| **Landing compression** | All 4 limbs `rotationPointY` compress + body sinks | ✅ Can use progressPosition during landing |
| **Ear independent flick** (keep) | Both ears with different flap freqs | ✅ Already present |
| **Glide body banking** (keep) | `body.rotateAngleZ` during glide | ✅ Already present |
| **Head stabilization** | `head.rotateAngleX` counter-rotates against body pitch | ✅ Head is independent bone |

### What CANNOT Be Solved
- ❌ **Membrane billowing/fluttering**: The membrane is a texture, not a bone. We can only position the limbs — the texture stretch is handled by the renderer.
- ❌ **Individual digit/claw articulation**: Limbs are single flat bones. No finger bones.
- ❌ **Independent eye movement**: No eye bones. Eyes are part of head texture.
- ❌ **Pouch young**: No pouch bone. Marsupial pouch is texture-only.

### Implementation Plan
1. **Add visible breathing**: `body.setScale(1+breath*0.015, 1+breath*0.02, 1+breath*0.015)`.
2. **Membrane deployment sequence during glide transition**: Phase 1 (0-2 progress): wrists extend outward `leftArm.rotateAngleZ -= 0.3F`. Phase 2 (2-4 progress): ankles follow `leftLeg.rotateAngleZ -= 0.25F`. Phase 3 (4-5 progress): full spread. Use progressRotation with stepped keyframes.
3. **Active tail curl during glide**: `tail.rotateAngleX = Mth.sin(ageInTicks * 0.3F) * 0.15F * glideProgress` (curling up/down) + `tail.rotateAngleY = Mth.sin(ageInTicks * 0.4F + 1F) * 0.1F * glideProgress` (side sway). More active than current pendulum.
4. **Vertical climb state**: Add climb progress. Body `rotateAngleX = Maths.rad(-70)`. Arms reach upward. Legs push. Tail curls against trunk. All driven by limbSwing.
5. **Landing compression**: When `glideProgress` transitions from high to low, body sinks `rotationPointY -= 0.4F` and limbs compress via `rotationPointY` during the transition.
6. **Differentiate ground walk**: Front arms use `swing(walkSpeed * 0.8F, walkDegree * 0.6F, ...)`. Rear legs use `swing(walkSpeed * 1.1F, walkDegree * 1.2F, ...)`. Tail actively counterbalances each step.

### Realistic Maximum: **6/10** (up from 3/10)
Ceiling limited by: no membrane bone (texture-only patagium), no finger bones, no eye bones. The 10 bones are fully utilized with these changes.

---

## A-4: RAIN FROG — Grumpy Burrower

### Biological Identity
Rain frogs are round, grumpy amphibians that do NOT hop — they walk with short, stubby steps. Their signature behavior is burrowing BACKWARD into soil using their hind legs to excavate. When threatened, they inflate their body to appear larger and emit a high-pitched squeak. Their face has a permanent frown (grumpy expression). They are poor swimmers and avoid water.

### Movement Identity vs Similar Creatures
- **vs WarpedToad**: Toad hops and shoots tongue. Rain frog walks and burrows backward. Toad is agile; rain frog is deliberate.
- **vs Stradpole**: Tadpole swims with tail undulation. Rain frog is terrestrial — no tail.

### Bone Inventory (VERIFIED from ModelRainFrog.java)
```
root
├── body              ← THE ENTIRE CREATURE. No separate head bone.
│   ├── tongue        ← Tongue for feeding
│   ├── left_arm      ← Tiny left front leg
│   ├── right_arm     ← Tiny right front leg
│   ├── left_leg      ← Slightly larger left rear leg (has 2 boxes = thigh+foot)
│   ├── right_leg     ← Slightly larger right rear leg
│   ├── left_eye      ← Left eye (protruding)
│   └── right_eye     ← Right eye (protruding)
```

9 bones. NO separate head bone — body IS head. NO throat sac bone — squeak inflation must be simulated via `body.setScale()`. Legs have 2 boxes (thigh area + foot flat extension) in a single bone.

### Planned Animations — Bone-Verified

| Animation | Bones Used | Feasibility |
|---|---|---|
| **Squeak inflation** | `body.setScale()` — body expands briefly then contracts | ✅ Scale can create inflation effect |
| **Backward burrow — leg scrape** | `left_leg`, `right_leg` with rearward kicking motion during burrow progress | ✅ Legs can rotate backward |
| **Lateral hop** (replace walk) | Both rear legs push simultaneously, front legs catch — coordinated hop cycle | ✅ All 4 limbs can synchronize for hop |
| **Grumpy head-tilt** | `body.rotateAngleZ` tilts sideways periodically at idle | ✅ Body is the head, so body tilt = head tilt |
| **Eye blink** (keep) | Both eyes via position offset | ✅ Already present |
| **Breathing** (keep) | `body.setScale()` + `rotationPointY` | ✅ Already present, can enhance |

### What CANNOT Be Solved
- ❌ **Throat sac inflation focus**: No separate throat bone. Inflation must be whole-body scale, which looks like the entire frog inflates, not just the throat.
- ❌ **Independent head rotation**: Body IS head. Can't look left without turning entire body.
- ❌ **Individual toe articulation**: Legs have 2 boxes in one bone — can't articulate toes separately.
- ❌ **Mouth/jaw articulation**: No jaw bone. Squeak is body inflation only.
- ❌ **Tongue projectile physics**: Already handled by `attackProgress` transitions.

### Implementation Plan
1. **Replace walk with lateral hop**: Rear legs push together: `this.swing(left_leg, hopSpeed, hopDegree*1.5F, false, 0F, -0.3F, limbSwing, limbSwingAmount)` and `this.swing(right_leg, hopSpeed, hopDegree*1.5F, true, 0F, -0.3F, limbSwing, limbSwingAmount)` — synchronized, not alternating. Front arms catch: `this.swing(left_arm, hopSpeed, hopDegree*0.8F, true, 1.5F, 0.2F, limbSwing, limbSwingAmount)` with opposite phase for landing absorption. Body bobs up during push-off: `this.bob(body, hopSpeed, hopDegree*3F, false, limbSwing, limbSwingAmount)`.
2. **Add squeak inflation body pulse**: When entity is in "stance" state (defensive), add rapid inflation-deflation cycle: `body.setScale(1 + squeakPulse*0.08F, 1 + squeakPulse*0.12F, 1 + squeakPulse*0.08F)` where `squeakPulse = Mth.abs(Mth.sin(ageInTicks * 1.5F)) * stanceProgress`.
3. **Add backward burrow leg scrape**: During burrow progress, legs kick backward: `left_leg.rotateAngleX += Mth.sin(ageInTicks * 1.2F) * 0.5F * digAmount` and same for right_leg. Legs also shift rearward: `left_leg.rotationPointZ -= digAmount * 2F`.
4. **Add grumpy idle head-tilt**: `body.rotateAngleZ = Mth.sin(ageInTicks * 0.05F) * 0.06F` — very slow, subtle side-to-side tilt. Body rotation = head tilt since there's no separate head.
5. **Enhance breathing**: Increase breath amplitude from 0.06F to 0.09F for more visible body movement. Keep existing setScale squash/stretch.

### Realistic Maximum: **5/10** (up from 3/10)
Ceiling limited by: no head bone (can't separate head from body), no throat sac, no jaw, 9 bones total. The round body is the model's charm and its limitation.

---

## A-5: ROCKY ROLLER — Armored Uncurler

### Biological Identity
Rocky Roller is an isopod-like creature (pillbug/woodlouse analog) that curls into an armored ball for protection. When uncurling, real isopods ripple their body segments sequentially from head to tail. They have multiple pairs of legs (7 pairs in real isopods, 2 pairs in this model). Their spikes provide protection when rolled. They roll downhill using gravity, using their body as a wheel.

### Movement Identity vs Similar Creatures
- **vs CaveCentipede**: Centipede has many legs in metachronal wave. Rocky Roller has few legs and rolls into a ball.
- **vs Lobster**: Lobster has claws and tail fan. Rocky Roller has spikes and curling.

### Bone Inventory (VERIFIED from ModelRockyRoller.java)
```
root
├── body              ← Main body segment
│   ├── HSpikes_r1    ← Horizontal spike row 1
│   ├── HSpikes_r2    ← Horizontal spike row 2
│   ├── VSpikes_r1    ← Vertical spike 1
│   ├── VSpikes_r2    ← Vertical spike 2
│   ├── VSpikes_r3    ← Vertical spike 3
│   ├── VSpikes_r4    ← Vertical spike 4
│   ├── tail          ← Tail segment
│   ├── head          ← Head segment
│   ├── left_arm      ← Front-left leg
│   ├── right_arm     ← Front-right leg
│   ├── left_leg      ← Rear-left leg
│   └── right_leg     ← Rear-right leg
```

14 bones. 6 SPIKE BONES that are currently barely used. Arms are front legs — they should participate in walking.

### Planned Animations — Bone-Verified

| Animation | Bones Used | Feasibility |
|---|---|---|
| **Arm participation in walk** | `left_arm`, `right_arm` with `walk()` matching leg phase but shorter stride | ✅ Arms are independent bones, can walk |
| **Uncurl segment ripple** | `head` → `body` → `tail` sequential `progressRotation` during unroll transition | ✅ Three segments can ripple |
| **Full spike bristle during roll** | All 6 spike bones: `HSpikes_r1/r2` rotate outward horizontally, `VSpikes_r1-4` rotate outward vertically | ✅ All 6 spike bones are independent |
| **Armored-ball idle rocking** | `body.rotateAngleX` slow oscillation while rolled | ✅ Body can rock while rolled |
| **Breathing** (keep) | `body.setScale()` | ✅ Already present |
| **Roll momentum** (keep) | `body.rotateAngleX` driven by roll time | ✅ Already present |

### What CANNOT Be Solved
- ❌ **Multiple body segments**: Only 3 segments (head/body/tail). Real isopods have 7+ segments. Uncurl ripple is limited to 3-step sequence.
- ❌ **Multiple leg pairs**: Only 2 pairs (arms + legs). Real isopods have 7 pairs.
- ❌ **Antenna bones**: No antenna bones exist. Head is a featureless block.
- ❌ **Compound eye articulation**: No eye bones.

### Implementation Plan
1. **Add arm walking**: `this.walk(left_arm, walkSpeed, walkDegree * 0.7F, true, 0.5F, 0.15F, limbSwing, limbSwingAmount)` and same for right_arm with opposite phase. Arms step in sync with the opposite-side leg.
2. **Add uncurl ripple**: When rollProgress decreases (uncurling), use progressRotation on head first, then body, then tail with staggered delays. Phase 1 (progress 0-2): head uncurls. Phase 2 (progress 2-4): body uncurls. Phase 3 (progress 4-5): tail uncurls.
3. **Full spike bristle during roll**: All 6 spike bones get flap/swing during roll: HSpikes_r1/r2 rotate outward (`rotateAngleY`), VSpikes_r1-4 bristle (`rotateAngleX` outward). Frequencies staggered for sequential ripple.
4. **Armored-ball idle rocking**: When fully rolled, `body.rotateAngleX += Mth.sin(ageInTicks * 0.3F) * 0.04F` for gentle momentum rocking.

### Realistic Maximum: **5/10** (up from 3/10)
Ceiling limited by: only 3 body segments, only 2 leg pairs, no antennae, no eyes.

---

## A-6: BUNFUNGUS — Mushroom Cap Wobbler

### Biological Identity
Bunfungus is a fungal-creature hybrid. Its defining feature is a mushroom cap on its head. Real mushroom caps have inertia — they wobble slightly after the stem moves. Fungi release spores through periodic puffs. The creature has a soft belly, expressive eyebrows, rabbit-like ears, and a snout. It's a gentle grazer that can jump and belly-slam.

### Movement Identity vs Similar Creatures
- **vs Mungus**: Mungus is a soft fungal blob with squash mechanics. Bunfungus has a rigid mushroom cap — the cap should have its own physics.
- **vs Mooshroom (vanilla)**: Bunfungus has a mushroom cap on its head like Mooshroom, but is a unique creature with jump/slam mechanics.

### Bone Inventory (VERIFIED from ModelBunfungus.java)
```
root
├── body              ← Main body
│   ├── belly         ← Soft underbelly (can expand for breathing)
│   ├── tail          ← Small tail
│   ├── head          ← Head
│   │   ├── left_brow    ← Left eyebrow (expressiveness!)
│   │   ├── right_brow   ← Right eyebrow (expressiveness!)
│   │   ├── shroom_cap   ← MUSHROOM CAP — THE KEY BONE. Child of head, currently STATIC.
│   │   │   ├── left_ear    ← Left ear on cap
│   │   │   └── right_ear   ← Right ear on cap
│   │   ├── snout       ← Snout
│   │   │   └── snout_r1 ← Snout tip
│   ├── left_arm       ← Front-left limb
│   ├── right_arm      ← Front-right limb
│   ├── left_leg       ← Rear-left upper
│   │   └── left_foot  ← Rear-left foot
│   └── right_leg      ← Rear-right upper
│       └── right_foot ← Rear-right foot
```

18 bones. `shroom_cap` IS A SEPARATE BONE from head — this is the key finding. It can wobble independently. Eyebrows exist and can be animated for expression.

### Planned Animations — Bone-Verified

| Animation | Bones Used | Feasibility |
|---|---|---|
| **Mushroom cap wobble** | `shroom_cap` with delayed `rotateAngleX` and `rotateAngleZ` tracking head movement with inertia | ✅ shroom_cap is a separate bone |
| **Spore puff body pulse** | `belly.setScale()` periodic expansion + `body.setScale()` slight puff | ✅ Belly is separate bone |
| **Ear follow-through** | `left_ear`, `right_ear` — delay ear motion behind head motion | ✅ Ears are separate bones |
| **Eyebrow expressiveness** | `left_brow`, `right_brow` micro-position during interested state | ✅ Eyebrows are separate bones |
| **Bouncy mushroom walk** | Body `bob()` with higher amplitude, belly compression on step impact | ✅ Modify existing walk parameters |
| **Snout sniffing** (keep) | `snout_r1` micro-flap | ✅ Already present |
| **Breathing** (keep) | `belly.setScale()` expansion | ✅ Already present |

### What CANNOT Be Solved
- ❌ **Gills under mushroom cap**: The cap is a solid block — no gill texture articulation.
- ❌ **Spore particle spawning**: This is particle system, not animation. We can only animate the body puff that would accompany spore release.
- ❌ **Mycelial network**: No root-like bones. "Rooting into ground" is conceptual only.
- ❌ **Cap growth/expansion**: Cap is a fixed bone. Scale changes make it look like the whole cap swells, not organic growth.

### Implementation Plan
1. **Add mushroom cap wobble**: The cap should lag behind head movement with its own inertia. `shroom_cap.rotateAngleX += Mth.sin(ageInTicks * 0.11F) * 0.06F` (independent wobble) + `shroom_cap.rotateAngleZ += Mth.sin(ageInTicks * 0.09F + 1.3F) * 0.04F`. During walk, cap bobs with slight delay: `shroom_cap.rotateAngleX += Mth.sin(limbSwing * walkSpeed + 0.5F) * walkDegree * 0.1F * limbSwingMod`. The 0.5F phase offset creates the delay.
2. **Add spore puff body pulse**: `belly.setScale(1 + sporePuff*0.06F, 1 + sporePuff*0.06F, 1)` where `sporePuff = Mth.abs(Mth.sin(ageInTicks * 0.06F))` — periodic pulse. `body.setScale(1 + sporePuff*0.02F, 1, 1 + sporePuff*0.02F)`.
3. **Add ear follow-through**: Instead of ears moving exactly with the head, add delayed phase: `this.flap(left_ear, idleSpeed, idleDegree * 1.2F, false, 1.5F, 0.2F, ageInTicks, 1.0F)` — the 1.5F offset delays ear motion behind the primary head motion.
4. **Differentiate mushroom walk from standard quadruped**: Increase body bob amplitude: `this.bob(body, walkSpeed, walkDegree * 3F, true, limbSwing, limbSwingMod)` — mushroom walks with a bouncy, springy gait. Belly compresses on foot impact.
5. **Eyebrow expression during interested state**: Already partially present. Add `left_brow.rotateAngleZ += Mth.sin(ageInTicks * 0.15F) * 0.03F` for subtle eyebrow micro-movement.

### Realistic Maximum: **6/10** (up from 4/10)
Ceiling limited by: cap is a solid block (no gills), no spore particle integration, no mycelial network. The 18 bones are well-utilized with these changes.

---

## A-7: MUDSKIPPER — Fin-Crutch Walker

### Biological Identity
Mudskippers are amphibious fish that WALK on land using their pectoral fins as CRUTCHES. The motion is: plant both fins forward → push body up and forward → lift fins → repeat. This is fundamentally different from flapping. Their eyes are on TOP of their head like periscopes and can rotate independently. They "skip" across mud by flicking their body and tail. The dorsal fin raises during threat displays. They breathe through their skin and gill chambers.

### Movement Identity vs Similar Creatures
- **vs FlyingFish**: FlyingFish swims and glides above water. Mudskipper walks on land with fins.
- **vs Triops**: Triops swims upside-down. Mudskipper walks upright on mud.
- **vs All other fish**: Mudskipper is the ONLY fish that walks on land using fins as crutches.

### Bone Inventory (VERIFIED from ModelMudskipper.java)
```
root
├── head              ← Head + body (the entire creature is basically "head")
│   ├── eyes          ← PERISCOPE EYES — separate bone! Can rotate independently.
│   ├── tail          ← Tail for swimming and mud-skipping
│   │   ├── dorsalFin ← Dorsal fin on back
│   │   └── tailFin   ← Tail fin
│   ├── leftFin       ← Left pectoral fin (CRUTCH for walking)
│   └── rightFin      ← Right pectoral fin (CRUTCH for walking)
```

8 bones. `eyes` IS A SEPARATE BONE from head — can rotate for periscope scanning. All fins are flat single bones. The entire creature is essentially a head with attached fins and tail.

### Planned Animations — Bone-Verified

| Animation | Bones Used | Feasibility |
|---|---|---|
| **Crutch walk cycle** | `leftFin`, `rightFin` with simultaneous plant-push-lift cycle (NOT alternating flap) | ✅ Fins are independent bones |
| **Periscope eye scanning** | `eyes` rotateAngleY independent from head | ✅ Eyes is a separate bone |
| **Mud-skip body flick** | `head` + `tail` coordinated flick motion | ✅ Head and tail can flick together |
| **Threat display — fin spread** | `dorsalFin`, `leftFin`, `rightFin` all spread simultaneously | ✅ All fins are independent |
| **Breathing** (keep) | `head.setScale()` | ✅ Already present |
| **Swimming undulation** (keep) | `chainSwing` on head→tail→tailFin | ✅ Already present |

### What CANNOT Be Solved
- ❌ **True independent eye stalks**: Eyes is a single bone for both eyes. Can rotate together but not independently (no left/right eye separation).
- ❌ **Gill chamber pulsation**: No gill bones. Breathing is simulated via body scale.
- ❌ **Pectoral fin "gripping" mud**: Fins are flat bones. Can position them but can't show gripping/clawing.
- ❌ **Individual fin ray articulation**: All fins are single flat bones.

### Implementation Plan
1. **Replace fin flap with crutch walk cycle**: The crutch cycle has 4 phases per step:
   - Phase A: Fins plant forward (`leftFin.rotateAngleX = -0.8F, rightFin.rotateAngleX = -0.8F`)
   - Phase B: Body pushes up and forward (`head.rotationPointY -= 0.3F, head.rotationPointZ += 0.2F`)
   - Phase C: Fins lift (`leftFin.rotateAngleX = 0.2F, rightFin.rotateAngleX = 0.2F`)
   - Phase D: Body settles, fins swing forward for next plant
   
   Implementation: Use `Mth.sin(limbSwing * walkSpeed)` for phase detection. When sin is rising (0→1): fins plant and push. When sin falls (1→0): fins lift and swing forward.
   ```java
   float crutchPhase = Mth.sin(limbSwing * walkSpeed);
   float pushPhase = Mth.clamp(crutchPhase, 0, 1); // 0→1 during push, 0 during lift
   float liftPhase = Mth.clamp(-crutchPhase, 0, 1); // 0→1 during lift
   leftFin.rotateAngleX += -0.8F * pushPhase + 0.3F * liftPhase;
   rightFin.rotateAngleX += -0.8F * pushPhase + 0.3F * liftPhase;
   head.rotationPointY -= 0.4F * pushPhase * limbSwingAmount;
   head.rotationPointZ += 0.3F * pushPhase * limbSwingAmount;
   ```
2. **Add periscope eye scanning**: `eyes.rotateAngleY = Mth.sin(ageInTicks * 0.15F) * 0.2F * (1 - limbSwingAmount * 0.5F)` — slow sweeping scan when stationary, reduced during movement.
3. **Add mud-skip body flick**: When on land and `limbSwingAmount > 0.7F` (fast movement), body and tail flick laterally: `head.rotateAngleY += Mth.sin(limbSwing * 2.5F) * 0.3F` and `tail.rotateAngleY -= Mth.sin(limbSwing * 2.5F + 0.3F) * 0.15F`.
4. **Add threat display**: During displayProgress, all fins spread: dorsalFin rotates up, pectoral fins spread wide, body arches upward.

### Realistic Maximum: **6.5/10** (up from 5/10)
Ceiling limited by: 8 bones, single eye bone (no independent eye stalks), flat fin bones. The crutch walk will be the most unique locomotion in the mod.

---

## A-8: FLYING FISH — Surface-to-Air Glider

### Biological Identity
Flying fish execute a remarkable 4-stage locomotion sequence: (1) underwater acceleration using subcarangiform body undulation, (2) surface break where the fish angles upward and breaks the water tension, (3) tail scull — the lower lobe of the tail fin (which is longer) sculls the water surface at ~50Hz for extra thrust, (4) glide — pectoral and pelvic fins spread as wings, body banks for steering, fins vibrate for stability. They can glide for 50+ meters.

### Movement Identity vs Similar Creatures
- **vs Mudskipper**: Mudskipper walks on land. FlyingFish glides above water — completely different.
- **vs All other fish**: Only fish in the mod that transitions from water to air.

### Bone Inventory (VERIFIED from ModelFlyingFish.java)
```
root
├── body              ← Main fish body
│   ├── tail          ← Tail/peduncle
│   │   └── tail_fin  ← Tail fin (heterocercal-like, single bone)
│   ├── left_pectoralFin  ← Left pectoral "wing"
│   ├── right_pectoralFin ← Right pectoral "wing"
│   ├── left_pelvicFin    ← Left pelvic fin (smaller, also spread for glide)
│   └── right_pelvicFin   ← Right pelvic fin
```

8 bones. Only 6 procedural calls currently. The model has the minimum bones needed. No separate head bone.

### Planned Animations — Bone-Verified

| Animation | Bones Used | Feasibility |
|---|---|---|
| **Subcarangiform swim** | body→tail→tail_fin `chainSwing` | ✅ Replace current tail-only swing with chain |
| **Takeoff sequence** | Pectoral fins tuck during swim → spread at surface | ✅ Fins can tuck then spread |
| **Tail scull during takeoff** | `tail_fin` rapid low-amplitude vibration during takeoff transition | ✅ Single bone, can oscillate rapidly |
| **Fin flutter during glide** | Both pectoral fins with rapid micro-flap (amplitude 0.03F, speed 2.5F) | ✅ Fins can flap at high frequency |
| **Visible breathing** | `body.setScale()` | ✅ Currently missing, easy to add |
| **Glide banking** (keep) | `body.rotateAngleZ` during glide | ✅ Already present |

### What CANNOT Be Solved
- ❌ **Separate head stabilization**: No separate head bone — body is head.
- ❌ **Streaming water droplets**: Particle system, not animation.
- ❌ **Wing camber/aerofoil shape change**: Fins are flat single bones.
- ❌ **Tail fin lower-lobe independent motion**: Tail fin is a single bone — upper and lower lobes can't move independently.
- ❌ **Pectoral fin overlap/phase during glide**: Only 2 wing bones — can't show wing-wake interaction.

### Implementation Plan
1. **Add subcarangiform swimming chain**: Replace current `swing(tail, ...)` with `AdvancedModelBox[] swimChain = {body, tail, tail_fin}; this.chainSwing(swimChain, swimSpeed, swimDegree*0.7F, -2, limbSwing, limbSwingAmount);`. Body now undulates, not just tail.
2. **Add takeoff sequence**: During flyProgress transition (0→5), define stages:
   - Progress 0-2: Pectoral fins tuck against body (`rotateAngleZ` inward)
   - Progress 2-3: Body angles up (`body.rotateAngleX = -0.3F`)
   - Progress 3-4: Tail sculls — `tail_fin.rotateAngleY += Mth.sin(ageInTicks * 3.5F) * 0.08F`
   - Progress 4-5: Pectoral fins spread (existing transition to 85°)
3. **Add fin flutter during glide**: When `flyProgress >= 5F`, add `this.flap(left_pectoralFin, 2.8F, 0.025F, true, 0, 0, ageInTicks, flyProgress*0.2F)` — rapid, tiny-amplitude flutter for stability. Same for right.
4. **Add visible breathing**: `body.setScale(1 + breath*0.015F, 1 + breath*0.02F, 1 + breath*0.015F)`.

### Realistic Maximum: **5/10** (up from 2/10)
Ceiling limited by: 8 bones, no head bone, single tail fin bone (no independent lobes), no gill bones. This is the best that 8 fish bones can do.

---

## A-9: LEAFCUTTER ANT — Pheromone Trail Follower

### Biological Identity
Leafcutter ants are colony workers that march in organized lines following chemical (pheromone) trails. They carry leaf fragments overhead in their mandibles. They use alternating tripedal gait — at any moment, 3 legs are on the ground forming a tripod (L1+R2+L3), then the other 3 (R1+L2+R3). At higher speeds, they switch to quadrupedal gait. Their antennae constantly tap the ground to follow pheromone trails. The carried leaf fragment should sway with the ant's movement (inertia/follow-through).

### Movement Identity vs Similar Creatures
- **vs Cockroach**: Cockroach scuttles erratically, hugs walls, avoids light. LeafcutterAnt marches in straight lines following trails. Cockroach has wings; ant does not.
- **vs TarantulaHawk**: Wasp is an aerial hunter. Ant is a ground worker.

### Bone Inventory (VERIFIED from ModelLeafcutterAnt.java)
```
root
├── body              ← Main body segment
│   ├── legfront_left     ← Front-left leg
│   ├── legfront_right    ← Front-right leg
│   ├── legmid_left       ← Mid-left leg
│   ├── legmid_right      ← Mid-right leg
│   ├── legback_left      ← Rear-left leg
│   ├── legback_right     ← Rear-right leg
│   ├── abdomen           ← Abdomen
│   └── head              ← Head
│       ├── leaf          ← Carried leaf fragment
│       │   └── leaf_r1   ← Leaf tip
│       ├── antenna_left  ← Left antenna
│       ├── antenna_right ← Right antenna
│       └── fangs         ← Mandibles
```

15 bones. `leaf` and `leaf_r1` exist — the carried leaf can sway independently. Fangs exist for idle mandible articulation.

### Planned Animations — Bone-Verified

| Animation | Bones Used | Feasibility |
|---|---|---|
| **Fix body bob jitter** | `body` — remove the `* 2F` speed multiplier | ✅ Simple parameter fix |
| **Differentiate leg degrees** | 6 legs with front: 0.7x, mid: 0.9x, rear: 1.2x | ✅ Just parameter changes |
| **Leaf sway inertia** | `leaf` and `leaf_r1` with delayed body movement | ✅ Leaf is separate bone from head |
| **Mandible idle articulation** | `fangs` micro-movement at idle | ✅ Fangs are separate bone |
| **Antenna ground-tapping** | Both antennae with downward bias during walk | ✅ Antennae can rotate downward |
| **Breathing** (keep) | `abdomen.setScale()` | ✅ Already present |
| **Tripedal gait** (keep) | Already correct phase offsets | ✅ Keep existing offset pattern |

### What CANNOT Be Solved
- ❌ **True pheromone trail following**: Antennae can tap ground but can't "know" trail direction without entity data.
- ❌ **Individual antennal segments**: Antennae are single flat bones (5x0 pixels).
- ❌ **Leg joint articulation**: Legs are single flat bones.
- ❌ **Leaf fragment flexibility**: Leaf is 2 connected bones — can pivot at the joint but can't bend along its length.

### Implementation Plan
1. **Fix body bob jitter**: Change `this.bob(body, walkSpeed * 2F, ...)` to `this.bob(body, walkSpeed, ...)` — bob at walk frequency, not double. This single change fixes the jitter.
2. **Differentiate leg degrees**: Front legs use `walkDegree * 0.7F` (short steps). Mid legs use `walkDegree * 0.9F`. Rear legs use `walkDegree * 1.15F` (power stroke).
3. **Add leaf sway inertia**: `leaf.rotateAngleY += Mth.sin(limbSwing * walkSpeed + 1.5F) * walkDegree * 0.15F * limbSwingAmount` — leaf sways with delayed phase from body. `leaf_r1.rotateAngleY += Mth.sin(limbSwing * walkSpeed + 2.0F) * walkDegree * 0.2F * limbSwingAmount` — leaf tip sways more (whip effect).
4. **Add mandible idle motion**: `this.walk(fangs, 0.15F, 0.04F, true, 0, 0.01F, ageInTicks, 1)` — tiny mandible clench/release.
5. **Add antenna ground-tap bias**: During walk, antennae angle slightly downward: `antenna_left.rotateAngleX -= 0.1F * limbSwingAmount`. Combined with existing swing + walk, this creates a ground-tapping pattern.
6. **Use unique offset constant**: Change `offsetleft = 2F` to `offsetleft = 2.7F` — different from TarantulaHawk's value. The tripedal pattern still works with any offset > 0.

### Realistic Maximum: **6/10** (up from 4/10)
Ceiling limited by: flat leg bones, flat antenna bones, no trail-following data. The leaf sway and bob fix will make a visible difference.

---

## A-10: CRIMSON MOSQUITO — Erratic Blood Hunter

### Biological Identity
Mosquitoes are famous for their ERRATIC flight pattern — unpredictable zigzag approach with sudden direction changes. Their wings beat at ~600Hz creating the characteristic whine. Females extend their proboscis to pierce skin and feed on blood. They have long, dangling legs during flight. Their antennae are plumose (feathery) and detect CO2. When full of blood, their abdomen visibly bloats.

### Movement Identity vs Similar Creatures
- **vs Fly**: Fly is smaller, has shorter mouth, rubs legs, and takes off/lands suddenly.
- **vs WarpedMosco**: WarpedMosco is a giant nether creature with 4 wings, tripedal ground walk, and arms. CrimsonMosquito is smaller, has 2 wings, and is purely aerial.
- **vs TarantulaHawk**: Wasp has search-pause walk. Mosquito is pure flyer.

### Bone Inventory (VERIFIED from ModelCrimsonMosquito.java)
```
root
├── body              ← Main body
│   ├── wingL         ← Left wing
│   ├── wingR         ← Right wing
│   ├── legsL         ← Left leg group
│   │   ├── legL1     ← Left leg 1
│   │   ├── legL2     ← Left leg 2
│   │   └── legL3     ← Left leg 3
│   ├── legsR         ← Right leg group
│   │   ├── legR1     ← Right leg 1
│   │   ├── legR2     ← Right leg 2
│   │   └── legR3     ← Right leg 3
│   ├── tail          ← Abdomen (blood-bloats)
│   └── head          ← Head
│       ├── antennaL  ← Left antenna
│       ├── antennaR  ← Right antenna
│       └── mouth     ← Proboscis (extends for feeding)
```

17 bones. `mouth` is a separate bone — can extend forward. 6 leg bones for dangling. `tail` already scales with blood level.

### Planned Animations — Bone-Verified

| Animation | Bones Used | Feasibility |
|---|---|---|
| **Erratic wing chaos** | `wingL`, `wingR` with dual-frequency modulation + chaos variable | ✅ Wings can have multi-frequency input |
| **Proboscis extension** | `mouth` `rotationPointZ` forward during feeding | ✅ mouth is independent bone |
| **Leg dangle differentiation** | 6 legs with unique amplitudes and phases | ✅ Each leg is independent |
| **Zigzag body position** | `body.rotationPointX` += chaotic offset during flight | ✅ Body can shift position |
| **Body vibration from wings** | `body.rotationPointX` += micro-jitter at wing frequency | ✅ Body is independent bone |
| **Antenna probing** (keep) | Both antennae | ✅ Already present, increase amplitude |
| **Blood bloat** (keep) | `tail.setScale()` | ✅ Already present |

### What CANNOT Be Solved
- ❌ **True aerodynamic chaos**: Can only approximate with multiple sine waves, not true chaotic dynamics. The motion will be pseudo-random but still deterministic.
- ❌ **Halteres (modified hindwings)**: No haltere bones — only 2 wing bones.
- ❌ **Proboscis piercing mechanics**: Mouth is a flat bone — can extend but can't show skin penetration.
- ❌ **CO2-sensing antenna plume**: Antennae are flat bones.

### Implementation Plan
1. **Add erratic wing chaos**: Create `float chaos1 = Mth.sin(ageInTicks * 0.7F) * Mth.cos(ageInTicks * 1.3F) * 0.15F` and `float chaos2 = Mth.sin(ageInTicks * 0.9F + 1.7F) * Mth.cos(ageInTicks * 1.1F) * 0.12F`. Add these to the wing flap: `this.flap(wingL, flySpeed * 3.3F, flyDegree + chaos1, true, 0, 0.2F + chaos2, ageInTicks, 1)`. The chaos variables create non-repeating variation.
2. **Add proboscis extension**: When `entity.getBloodLevel() > 0` or during feeding, `mouth.rotationPointZ -= 0.5F` (extend forward) and `mouth.rotateAngleX -= 0.2F` (angle down for piercing).
3. **Differentiate leg dangle**: Give each leg unique amplitude: `legL1: 0.4F, legL2: 0.35F, legL3: 0.3F, legR1: 0.45F, legR2: 0.32F, legR3: 0.28F` with different phase offsets. Legs dangle unevenly like a real mosquito.
4. **Add zigzag body position**: `body.rotationPointX += Mth.sin(ageInTicks * 1.1F) * 0.8F * flyProgress * 0.2F + Mth.sin(ageInTicks * 2.3F + 2F) * 0.4F * flyProgress * 0.2F` — two frequencies for chaotic lateral movement.
5. **Add body vibration from wings**: `body.rotationPointX += Mth.sin(ageInTicks * flySpeed * 3.3F) * 0.015F` — micro-jitter synchronized with wing beat.
6. **Increase antenna amplitude**: Change from `walk(flySpeed, flyDegree * 0.15F, ...)` to `walk(flySpeed, flyDegree * 0.35F, ...)` — more visible probing.

### Realistic Maximum: **5/10** (up from 3/10)
Ceiling limited by: no haltere bones, flat mouth bone, deterministic chaos. The multi-frequency chaos will create convincing pseudo-random flight.

---

## A-11: FLY — Leg-Rubbing Pest

### Biological Identity
Houseflies are famous for rubbing their front legs together (cleaning behavior). They have erratic, zigzag flight. They land suddenly on surfaces and take off explosively. Their proboscis extends to feed on liquids. Their wings beat at ~200Hz. They are attracted to decay and waste.

### Movement Identity vs Similar Creatures
- **vs CrimsonMosquito**: Mosquito seeks blood with proboscis. Fly seeks food with sponging mouthparts. Fly rubs legs; mosquito does not.
- **vs All other insects**: Fly is the smallest model (6 bones). The ceiling is extremely low.

### Bone Inventory (VERIFIED from ModelFly.java)
```
root
├── body              ← Main body
│   ├── legs          ← ALL LEGS IN ONE BONE — cannot articulate individually
│   ├── left_wing     ← Left wing
│   ├── right_wing    ← Right wing
│   └── mouth         ← Proboscis
```

6 bones. `legs` is a SINGLE BONE for all legs. This is THE most limiting model in the entire mod.

### Planned Animations — Bone-Verified

| Animation | Bones Used | Feasibility |
|---|---|---|
| **Leg-rubbing** | `legs` rotateAngleX oscillation — simulates front legs rubbing | ✅ legs can rotate, simulating rub motion |
| **Proboscis extension** | `mouth` rotationPointZ forward | ✅ mouth is independent bone |
| **Erratic wing chaos** | Both wings with multi-frequency modulation | ✅ Same technique as mosquito |
| **Sudden takeoff** | Body lurches upward during launch | ✅ body can shift position |
| **Breathing** (keep) | `body.setScale()` | ✅ Already present |

### What CANNOT Be Solved
- ❌ **Individual leg articulation**: `legs` is ONE bone for all legs. Front/mid/rear leg differentiation is physically impossible.
- ❌ **True leg-rubbing with two legs**: Can only rotate the entire leg assembly — looks like all legs move, not just front two.
- ❌ **Wing halteres**: No haltere bones.
- ❌ **Head articulation**: No separate head bone.
- ❌ **Compound eye detail**: No eye bones.

### Implementation Plan
1. **Add leg-rubbing**: `legs.rotateAngleX += Mth.sin(ageInTicks * 1.8F) * 0.25F` when `flag` (grounded). Also add `legs.rotateAngleZ += Mth.cos(ageInTicks * 1.8F) * 0.15F` for multi-axis rub. The rub only plays when the fly is grounded.
2. **Add proboscis extension**: `mouth.rotationPointZ -= 0.3F` and `mouth.rotateAngleX -= 0.3F` periodically: `mouth.rotationPointZ -= Mth.abs(Mth.sin(ageInTicks * 0.3F)) * 0.6F`.
3. **Add erratic flight chaos**: Same multi-frequency technique as mosquito but with different frequencies: `chaos1 = Mth.sin(ageInTicks * 0.9F) * Mth.cos(ageInTicks * 1.5F) * 0.2F`.
4. **Add sudden takeoff**: When transitioning from ground to air, body does a quick upward lurch: simulated by the existing wing flap activation triggering immediate lift.

### Realistic Maximum: **4/10** (up from 2/10)
Ceiling is MODEL-LIMITED at 4/10. 6 bones with a single "legs" bone means individual leg articulation is impossible. The leg-rubbing will look like the entire leg assembly wiggling, which is the best approximation possible.

---

## A-12: PLATYPUS — Electro-Sensing Forager

### Biological Identity
Platypus is a monotreme (egg-laying mammal) with a duck-like bill that detects electric fields from prey. It sweeps its bill side-to-side like a metal detector while foraging underwater. Its front feet are webbed for swimming (primary propulsion), while rear feet have claws for digging and steering. Its tail stores fat and acts as a rudder. It closes its eyes, ears, and nostrils when diving. It has a fedora hat (the mod's playful touch).

### Movement Identity vs Similar Creatures
- **vs Anteater**: Anteater has a long tubular snout for eating ants. Platypus has a flat bill for electroreception.
- **vs SeaBear**: SeaBear is a large aquatic predator. Platypus is a small foraging monotreme.

### Bone Inventory (VERIFIED from ModelPlatypus.java)
```
root
├── body              ← Main body
│   ├── head          ← Head
│   │   ├── beak      ← THE BILL — separate bone. Can swing side-to-side!
│   │   └── fedora    ← Hat (separate bone, can bob)
│   ├── arm_left      ← Front-left limb (webbed)
│   ├── arm_right     ← Front-right limb (webbed)
│   ├── leg_left      ← Rear-left limb (clawed)
│   ├── leg_right     ← Rear-right limb (clawed)
│   └── tail          ← Flat tail (fat storage)
```

10 bones. `beak` IS A SEPARATE BONE from head — this is the KEY finding. The bill can sweep side-to-side independently.

### Planned Animations — Bone-Verified

| Animation | Bones Used | Feasibility |
|---|---|---|
| **Bill-sweep electroreception** | `beak` with `rotateAngleY` side-to-side sweep | ✅ beak is independent bone |
| **Breathing** | `body.setScale()` + `rotationPointY` | ✅ Currently ENTIRELY MISSING |
| **Front-limb primary swim** | `arm_left/right` with higher degree for paddle stroke | ✅ Just parameter change |
| **Rear-limb steering** | `leg_left/right` with lower degree for rudder | ✅ Just parameter change |
| **Tail fat-storage jiggle** | `tail` with independent inertia oscillation | ✅ tail is independent bone |
| **Fedora bob** | `fedora` micro-bob — playful detail | ✅ fedora is separate bone |
| **Dig state** (keep) | All limbs during dig progress | ✅ Already present |

### What CANNOT Be Solved
- ❌ **Webbed foot membrane**: Feet are flat single bones. Webbing is texture-only.
- ❌ **Venomous spur on hind leg**: No spur bone. The venom spur is texture or entity logic.
- ❌ **Electroreceptor pores on bill**: Bill is a solid block. Pores are texture.
- ❌ **Eye/ear/nostril closing when diving**: No separate eyelid/ear bones.
- ❌ **Egg-laying posture**: Can be added as a state if entity supports it, but animation requires new progress data.

### Implementation Plan
1. **Add breathing** (CRITICAL — currently absent): `float breath = Mth.cos(ageInTicks * 0.12F); body.setScale(1 + breath*0.015F, 1 + breath*0.02F, 1 + breath*0.015F); body.rotationPointY += breath * 0.05F;`.
2. **Add bill-sweep for electroreception**: When `entity.isSensing()` or `entity.isSensingVisual()`, replace current head swing with bill sweep: `beak.rotateAngleY = Mth.sin(ageInTicks * 0.2F) * 0.3F * (entity.isSensing() ? 1.5F : 1.0F)`. The bill sweeps side-to-side independently from the head. Head stabilizes: `head.rotateAngleY += netHeadYaw * 0.3F * Mth.DEG_TO_RAD` (reduced tracking while sensing — platypus relies on electroreception, not vision, when hunting).
3. **Differentiate front/rear limb swim**: Front webbed feet are primary paddles: `arm_left/right` with `flap(swimSpeed, swimDegree * 1.3F, ...)` — high amplitude paddle. Rear clawed feet are rudders: `leg_left/right` with `flap(swimSpeed, swimDegree * 0.5F, ...)` — low amplitude steering. Also add rear-foot counter-steering: `leg_left.rotateAngleZ += Mth.sin(limbSwing * swimSpeed + 1F) * 0.15F * limbSwingAmount` for subtle rudder action.
4. **Add tail inertia jiggle**: `tail.rotateAngleX += Mth.sin(ageInTicks * 0.08F) * 0.04F` (independent slow jiggle) + `tail.rotateAngleZ += Mth.sin(limbSwing * walkSpeed + 2F) * 0.05F * limbSwingAmount` (walk-driven sway with delay). The fat-storing tail should have its own physics.
5. **Add fedora bob**: `fedora.rotationPointY += Mth.sin(ageInTicks * 0.1F) * 0.03F` — playful hat bob.

### Realistic Maximum: **5/10** (up from 3/10)
Ceiling limited by: flat feet bones (no webbing articulation), solid bill (no electroreceptor pore animation), no spur bone. The bill sweep will be unique to this creature.

---

## A-13: SKELEWAG — Death-Rattle Serpent

### Biological Identity
Skelewag is an undead skeletal fish-like creature. Its identity comes from its vertebrae segments rattling and its jaw clacking. As an undead, it should have erratic, twitchy motion — not the smooth undulation of a living fish. Its sail (flag) should ripple in water currents. When it dies, body parts fall apart.

### Movement Identity vs Similar Creatures
- **vs FrilledShark**: FrilledShark is a living eel-like swimmer with smooth undulation. Skelewag is undead — should have chaotic, twitchy motion with bone-rattling.
- **vs All living fish**: Skelewag is the only undead fish — its motion should be distinctly unnatural.

### Bone Inventory (VERIFIED from ModelSkelewag.java)
```
root
├── body              ← Main body segment
│   ├── head          ← Skull. Can clack.
│   ├── flag          ← Sail/flag on back
│   ├── left_fin      ← Left fin
│   ├── right_fin     ← Right fin
│   ├── tail          ← Tail segment
│   │   └── tail_fin  ← Tail fin
```

8 bones. No separate jaw bone — "jaw clack" must be simulated by head micro-rotation. `flag` is a sail-like flat bone.

### Planned Animations — Bone-Verified

| Animation | Bones Used | Feasibility |
|---|---|---|
| **Body micro-vibration** | `body` with high-frequency, low-amplitude jitter for "vertebrae rattle" | ✅ body can vibrate via rotation offsets |
| **Jaw clack at idle** | `head` with periodic micro-rotation on Z-axis | ✅ head can micro-rotate |
| **Flag ripple** | `flag` with `chainSwing`-like wave (but single bone — use multi-freq flap) | ✅ flag is independent bone |
| **Fin asymmetry** | `left_fin` and `right_fin` with slightly different frequencies | ✅ Both fins are independent |
| **Visible breathing** | `body.setScale()` | ✅ Currently missing, add setScale |
| **Swimming** (keep) | `chainSwing` on body→tail→tail_fin | ✅ Already present |

### What CANNOT Be Solved
- ❌ **Individual vertebrae rattle**: Body is a single bone — can vibrate as a unit but can't show individual vertebra movement.
- ❌ **Rib cage expansion**: No rib bones.
- ❌ **Separate upper/lower jaw**: Head is a single bone — jaw clack is head twitch.
- ❌ **Bone separation during death**: Already handled by progressPosition on each part.

### Implementation Plan
1. **Add body micro-vibration**: `body.rotationPointX += Mth.sin(ageInTicks * 4.5F) * 0.03F + Mth.sin(ageInTicks * 7.3F) * 0.02F` — dual-frequency jitter creates the "bone rattle" effect. Intensity increases with `limbSwingAmount`.
2. **Add jaw idle clack**: `head.rotateAngleZ += (Mth.sin(ageInTicks * 1.1F) > 0.85F ? Mth.sin(ageInTicks * 1.1F) * 0.04F : 0)` — threshold-triggered periodic clack (not continuous, but sudden).
3. **Add flag ripple**: `this.flap(flag, 0.25F, 0.08F, false, 0, 0.03F, ageInTicks, 1); this.flap(flag, 0.15F, 0.06F, true, 2, 0.02F, ageInTicks, 1)` — dual-frequency flap creates a non-repeating ripple pattern on the single flat sail bone.
4. **Add fin asymmetry**: Left fin: `this.flap(left_fin, 0.15F, 0.05F, false, 2, 0.02F, ageInTicks, 1)`. Right fin: `this.flap(right_fin, 0.17F, 0.06F, true, 2.3F, 0.03F, ageInTicks, 1)`. Slightly different frequencies and amplitudes.
5. **Add visible breathing**: `body.setScale(1 + breath*0.015F, 1, 1 + breath*0.015F)`.

### Realistic Maximum: **5/10** (up from 3/10)
Ceiling limited by: single body bone (no individual vertebrae), single head bone (no jaw separation), 8 bones total. The bone rattle and jaw clack will give it unique undead identity.

---

## A-14: LOBSTER — Sideways Scuttler

### Biological Identity
Lobsters are decapod crustaceans that primarily move SIDEWAYS (not forward). They have 4 pairs of walking legs (pereiopods) that move in metachronal rhythm, and 1 pair of large claws (chelipeds). Their antennae are primary sensory organs — they sweep, probe, and chemically sense the substrate. Their tail fan (uropods + telson) enables the caridoid escape reaction — a rapid tail curl that shoots them backward. Claws snap with an explosive mechanism: slow cocking (open wide) → latch → instant snap.

### Movement Identity vs Similar Creatures
- **vs MantisShrimp**: Mantis shrimp has club-like raptorial appendages that punch. Lobster has crushing claws that snap.
- **vs Crawfish (not in mod)**: Similar body plan but lobster has proportionally larger claws.

### Bone Inventory (VERIFIED from ModelLobster.java)
```
root
├── body              ← Main body (cephalothorax)
│   ├── antenna_left      ← Left antenna
│   ├── antenna_right     ← Right antenna
│   ├── arm_left          ← Left claw arm
│   │   └── hand_left     ← Left claw
│   ├── arm_right         ← Right claw arm
│   │   └── hand_right    ← Right claw
│   ├── tail              ← Abdomen segment
│   │   └── tail2         ← Tail fan (uropods + telson as single bone)
│   ├── legs_left         ← ALL LEFT WALKING LEGS in one bone
│   └── legs_right        ← ALL RIGHT WALKING LEGS in one bone
```

12 bones. `legs_left` and `legs_right` are SINGLE BONES each — all 4 walking legs per side move as one unit. Claws have separate arm + hand bones — articulation possible.

### Planned Animations — Bone-Verified

| Animation | Bones Used | Feasibility |
|---|---|---|
| **Sideways scuttle bias** | `body.rotationPointX` += lateral offset during walk; `body.rotateAngleY` slight turn | ✅ body can shift laterally |
| **Claw snap wind-up + strike** | `arm_left/right` + `hand_left/right` with two-stage attack animation | ✅ Claws have separate arm/hand bones |
| **Tail-fan escape burst** | `tail` + `tail2` rapid curl during escape | ✅ Both tail bones can curl |
| **Antenna substrate dip** | Both antennae with downward bias periodically | ✅ Antennae are independent |
| **Walk speed fix** | Reduce from 3F to 0.7F | ✅ Simple parameter change |
| **Scuttle rock** (keep) | `body.rotationPointX += scuttleRock` | ✅ Already present, increase amplitude |
| **Breathing** (keep) | `body.setScale()` | ✅ Already present |

### What CANNOT Be Solved
- ❌ **Individual walking leg articulation**: `legs_left` and `legs_right` are single bones — all 4 legs per side move identically.
- ❌ **Tail fan uropod spread**: `tail2` is a single flat bone — uropods can't spread individually.
- ❌ **Claw crushing force display**: Claws can snap open/closed but can't show gripping/deformation.
- ❌ **Mouthpart (maxilliped) animation**: No mouthpart bones.
- ❌ **Eye stalk movement**: No eye bones.

### Implementation Plan
1. **Add sideways scuttle bias**: During walk, `body.rotationPointX += Mth.sin(limbSwing * walkSpeed) * 0.5F * limbSwingAmount` — body sways laterally (sideways scuttle). `body.rotateAngleY += Mth.sin(limbSwing * walkSpeed + 1F) * 0.08F * limbSwingAmount` — body turns slightly into the scuttle direction.
2. **Add claw snap wind-up**: The attack animation currently goes directly to snap position. Add anticipation: when attackProgress transitions from 0→2.5, claws OPEN WIDE first (`hand_left.rotateAngleZ` outward, `hand_right.rotateAngleZ` outward). Then from 2.5→5, claws SNAP shut with a rapid rotation. This creates the wind-up → strike sequence.
3. **Add tail-fan escape burst**: When `limbSwingAmount > 0.8F` (fast movement underwater), tail curls rapidly: `tail.rotateAngleX -= 0.4F` and `tail2.rotateAngleX -= 0.3F` — the caridoid escape reaction. This only triggers at high speed.
4. **Reduce walk speed**: Change `walkSpeed = 3F` to `walkSpeed = 0.7F` — lobsters don't skitter. Add `walkDegree = 0.8F` for more visible leg movement.
5. **Add antenna substrate dip**: Periodically, antennae dip downward: `antenna_left.rotateAngleX -= Mth.abs(Mth.sin(ageInTicks * 0.08F)) * 0.2F` — dips to "taste" substrate, returns up.
6. **Increase scuttle rock amplitude**: Change from `walkDegree * 0.25F` to `walkDegree * 0.45F` for more visible lateral rocking.

### Realistic Maximum: **5.5/10** (up from 4/10)
Ceiling limited by: single leg bones per side (no individual pereiopod motion), single tail fan bone, no eye stalks. The claw snap wind-up and tail-fan escape will significantly improve identity.

---

## A-15: MANTIS SHRIMP — Spring-Loaded Puncher

### Biological Identity
Mantis shrimp (stomatopod) has the FASTEST punch in the animal kingdom — accelerating at 10,400g to strike with the force of a .22 caliber bullet. The punch mechanism is latch-mediated spring actuation: (1) SLOW cocking phase — muscles contract to compress a saddle-shaped spring in the raptorial appendage, (2) LATCH holds the stored energy, (3) INSTANT release — the latch disengages and the club strikes in microseconds. They have the most sophisticated eyes in the animal kingdom — each eye has trinocular vision and can move independently on stalks. Their body shimmers with iridescent rainbow colors.

### Movement Identity vs Similar Creatures
- **vs Lobster**: Lobster has crushing claws that snap. Mantis shrimp has spring-loaded clubs that PUNCH — fundamentally different mechanism.
- **vs All other crustaceans**: Mantis shrimp eyes track independently (verified in entity data — `leftEyePitch/Yaw` and `rightEyePitch/Yaw` already separate!).

### Bone Inventory (VERIFIED from ModelMantisShrimp.java)
```
root
├── body              ← Main body
│   ├── head          ← Head
│   │   ├── flapper_left   ← Left swimming flapper
│   │   ├── flapper_right  ← Right swimming flapper
│   │   ├── eye_left       ← LEFT EYE — independent from right!
│   │   ├── eye_right      ← RIGHT EYE — independent from left!
│   │   ├── arm_left       ← Left raptorial arm
│   │   │   └── fist_left  ← Left club
│   │   ├── arm_right      ← Right raptorial arm
│   │   │   └── fist_right ← Right club
│   │   ├── whisker_left   ← Left antennule
│   │   └── whisker_right  ← Right antennule
│   ├── tail              ← Abdomen
│   ├── legs_front        ← Front walking legs (single bone)
│   └── legs_back         ← Rear walking legs (single bone)
```

16 bones. `eye_left` and `eye_right` ARE SEPARATE BONES with separate pitch/yaw data from entity. This is THE best eye system in the mod. `fist_left` and `fist_right` are separate from arms — clubs can articulate.

### Planned Animations — Bone-Verified

| Animation | Bones Used | Feasibility |
|---|---|---|
| **Punch cocking phase** | `arm_left/right` + `fist_left/right` — two-stage: slow retract (0→3 progress), INSTANT strike (3→5) | ✅ Arms and fists are separate bones |
| **Rainbow body shimmer** | `body.setScale()` with multi-axis asynchronous pulses | ✅ body can scale on multiple axes |
| **Eye independent scanning at idle** | `eye_left`, `eye_right` with autonomous sweep when not tracking | ✅ Eyes have separate data pipes already |
| **Impact recoil** | `arm_left/right` bounce back after strike | ✅ Arms can oscillate post-strike |
| **Flapper threat display** | `flapper_left/right` spread wide | ✅ Flappers are independent bones |
| **Whisker differentiation** | Different frequencies for left vs right | ✅ Already possible, increase differentiation |
| **Breathing** (keep) | `body.setScale()` | ✅ Already present |

### What CANNOT Be Solved
- ❌ **True instantaneous strike**: Animation runs at game tick rate (20 ticks/sec). The fastest possible transition is 1 tick (50ms). Real strike is 2-5ms. We can approximate with a 2-tick strike (visually instant at Minecraft framerate).
- ❌ **Individual eye ommatidia**: Eyes are cube blocks — can't show the compound eye structure.
- ❌ **Saddle-spring mechanism visualization**: The spring mechanism is internal. Can only show external motion (cock → latch → strike).
- ❌ **Individual walking leg articulation**: `legs_front` and `legs_back` are single bones each.
- ❌ **Cavitation bubble**: The shockwave from the punch creates cavitation bubbles — this is particle/effect system, not animation.

### Implementation Plan
1. **Add two-stage punch**: Modify the punchProgress transition to have distinct phases:
   - Progress 0→3 (slow cocking): `arm_right.rotateAngleX` slowly increases (club retracts). Duration: ~150ms (3 ticks).
   - Progress 3→3.5 (latch hold): Pause at fully cocked position. Duration: ~25ms (0.5 tick — visual micro-pause).
   - Progress 3.5→5 (INSTANT strike): `fist_right.rotateAngleX` rapidly rotates forward. Duration: ~75ms (1.5 ticks — as fast as Minecraft allows).
   
   Implement with stepped progressRotation calls at different thresholds, not uniform progress.
2. **Add impact recoil**: After strike (`punchProgress >= 4.5F`), arm bounces back: `arm_right.rotateAngleX += Mth.sin((punchProgress - 4.5F) * 10F) * 0.15F` — rapid oscillation that decays.
3. **Add rainbow shimmer**: `body.setScale(1 + Mth.sin(ageInTicks * 0.17F) * 0.008F, 1 + Mth.cos(ageInTicks * 0.13F) * 0.006F, 1 + Mth.sin(ageInTicks * 0.11F + 1.5F) * 0.007F)` — three different frequencies on three axes create a shifting, iridescent shimmer effect. Very subtle (under 1%) but creates the rainbow play-of-color.
4. **Add eye autonomous scanning**: When entity is NOT tracking a target (punchProgress == 0), eyes scan independently:
   ```java
   if (punchProgress == 0 && swimProgress == 0) {
       eye_left.rotateAngleY += Mth.sin(ageInTicks * 0.18F) * 0.2F;
       eye_left.rotateAngleX += Mth.sin(ageInTicks * 0.13F + 1F) * 0.1F;
       eye_right.rotateAngleY += Mth.sin(ageInTicks * 0.22F + 1.5F) * 0.2F;
       eye_right.rotateAngleX += Mth.sin(ageInTicks * 0.15F + 2.5F) * 0.1F;
   }
   ```
   Different frequencies per eye. The existing tracking data (`leftEyePitch/Yaw`, `rightEyePitch/Yaw`) overrides this when a target is present.
5. **Add flapper threat display**: When threatened (punchProgress > 0), flappers spread: `flapper_left.rotateAngleZ += 0.3F; flapper_right.rotateAngleZ -= 0.3F`.
6. **Differentiate whisker frequencies**: Left: `walk(whisker_left, 0.15F, 0.35F, false, 0, -0.25F, ageInTicks, 1)`. Right: `walk(whisker_right, 0.18F, 0.28F, true, 0, 0.25F, ageInTicks, 1)`.

### Realistic Maximum: **6/10** (up from 4/10)
Ceiling limited by: single leg bones (no pereiopod articulation), cube eyes (no ommatidia), internal spring not visible. The two-stage punch with cocking+instant-strike and the independent eye scanning will make this one of the most distinctive creatures in the mod.

---

## SUMMARY: PRIORITY A — VERIFIED FEASIBILITY

| # | Creature | Bones | Current | Ceiling | Key Limitation |
|---|---|---|---|---|---|
| 1 | Cockroach | 14 | 4 | 6.5 | Flat legs, single wing bone, no cerci |
| 2 | Terrapin | 13 | 3 | 5 | No neck vertebrae, flat feet |
| 3 | SugarGlider | 10 | 3 | 6 | No membrane bone (texture), no fingers |
| 4 | RainFrog | 9 | 3 | 5 | No head bone (body=head), no throat sac |
| 5 | RockyRoller | 14 | 3 | 5 | Only 3 segments, 2 leg pairs |
| 6 | Bunfungus | 18 | 4 | 6 | Cap is solid block, no gills |
| 7 | Mudskipper | 8 | 5 | 6.5 | Single eye bone, flat fins |
| 8 | FlyingFish | 8 | 2 | 5 | No head bone, single tail fin bone |
| 9 | LeafcutterAnt | 15 | 4 | 6 | Flat legs, no trail data |
| 10 | CrimsonMosquito | 17 | 3 | 5 | No halteres, flat mouth |
| 11 | Fly | 6 | 2 | 4 | SINGLE legs bone — severely limited |
| 12 | Platypus | 10 | 3 | 5 | Flat feet, solid bill |
| 13 | Skelewag | 8 | 3 | 5 | Single body bone, no jaw bone |
| 14 | Lobster | 12 | 4 | 5.5 | Single leg bones per side |
| 15 | MantisShrimp | 16 | 4 | 6 | Single leg bones, cube eyes |

### Verdict
- **Every planned improvement is bone-verified** — no invented bones, no model changes needed
- **All creatures can reach their ceilings** with parameter changes and new animation logic only
- **Zero model hierarchy modifications required**
- **All existing ModelAnimator sequences and progress transitions preserved**
- **Estimated total implementation time**: 18 hours
