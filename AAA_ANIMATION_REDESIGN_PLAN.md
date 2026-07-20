# AAA_ANIMATION_REDESIGN_PLAN.md
## Alex's Mobs — Priority S & A Redesign Blueprint
**Lead Animation Director** | **Date**: 2026-07-20

---

## EXECUTION ORDER

Ordered by impact × feasibility. Highest-impact creatures with largest gap between current score and potential ceiling come first.

| # | Creature | Current | Ceiling | Gap | Difficulty | Est. Time |
|---|---|---|---|---|---|---|
| 1 | 🦭 Seal | 3 | 6 | 3 | MEDIUM | 45 min |
| 2 | 🐛 CaveCentipede | 2 | 6 | 4 | HIGH | 90 min |
| 3 | 🦠 Enderiophage | 2 | 7 | 5 | MEDIUM | 45 min |
| 4 | 🦇 Skreecher | 2 | 6 | 4 | MEDIUM | 45 min |
| 5 | 🐝 TarantulaHawk | 3 | 7 | 4 | MEDIUM | 30 min |
| 6 | 🌌 Cosmaw | 3 | 6 | 3 | LOW | 20 min |
| 7 | 🐟 Catfish×3 | 2 | 5 | 3 | LOW | 30 min |
| 8 | 🐉 Laviathan | 3 | 6 | 3 | LOW | 20 min |
| 9 | 🦅 SoulVulture | 3 | 6 | 3 | LOW | 20 min |
| 10 | 🦐 Triops | 2 | 5 | 3 | LOW | 20 min |

---

## 1. 🦭 SEAL — Priority S #1

### Why current animation is weak (3/10)
- **FUNDAMENTAL BIOMECHANICS ERROR**: Uses `chainWave(head, body, tail)` for swimming. `chainWave` produces **lateral** undulation (side-to-side, like fish/snakes). Real seals (phocids) use **vertical** pelvic undulation — the rear body oscillates up-and-down like a dolphin, with hind flippers providing thrust.
- Arm flippers get identical `flap()` calls with identical parameters — left and right are mirrors with no independent steering.
- Land locomotion is a hardcoded formula buried in `Math.sin() * 9D` rather than procedural belly-bounce mechanics.
- Only whisker twitch as secondary motion. No blubber ripple, no surface-breaching head lift.
- 54 `progressRotation/Position` calls — most in project — but they represent 5 basking poses and digging state, not procedural quality.

### Biological identity that must appear
**Phocid seal**: Pelvic undulation swimmer. Front flippers = steering (not propulsion). Hind flippers = thrust. On land: belly-bounces like a giant caterpillar — body arches up, lunges forward, belly slides. Whiskers detect fish vibrations. Basks on ice/rocks in various poses. Deep diver — surfaces with explosive exhale.

### New animation system design

```
SWIMMING (replaces chainWave):
  body.rotateAngleX += sin(limbSwing * swimSpeed) * swimDegree  [VERTICAL pitch]
  tail.rotateAngleX += sin(limbSwing * swimSpeed - 0.4) * swimDegree * 0.7  [phase-delayed]
  rearLegs.rotateAngleX += sin(limbSwing * swimSpeed - 0.7) * swimDegree * 1.2  [flipper thrust]
  // Front flippers = independent steering
  leftArm.rotateAngleZ += sin(ageInTicks * 0.3) * 0.15  [slow steering]
  rightArm.rotateAngleZ -= sin(ageInTicks * 0.3 + 0.5) * 0.15
  // Speed-dependent undulation amplitude
  float speedFactor = entity.getDeltaMovement().length() * 0.5
  swimDegree *= (1.0 + speedFactor)

LAND WALK (belly-bounce):
  // Arch body up → lunge forward → belly slides
  float archPhase = sin(limbSwing * walkSpeed)
  body.rotateAngleX += archPhase > 0 ? archPhase * 0.3 : 0  [only arch UP]
  body.rotationPointZ += archPhase > 0 ? archPhase * 2.0 : 0  [lunge forward]
  // Front flippers pull body forward
  leftArm.rotateAngleZ += archPhase > 0 ? archPhase * 0.5 : 0
  rightArm.rotateAngleZ -= archPhase > 0 ? archPhase * 0.5 : 0
  // Belly slides on down-phase (no rotation, just translation)
  body.rotationPointY += archPhase < 0 ? -archPhase * 1.5 : 0
```

### Files to modify
- `ModelSeal.java` — `setupAnim()` method only
- No model hierarchy changes
- No entity changes
- No renderer changes

### Required animation states
- Idle (whisker twitch + body breath + slow head scan): ✓ EXISTS
- Swim (vertical undulation): ✗ REDESIGN
- Land walk (belly-bounce): ✗ REDESIGN
- Basking (5 poses): ✓ EXISTS (preserved)
- Digging: ✓ EXISTS (preserved)
- Surface breach / exhale: ✗ NEW (subtle head-lift + body shudder)

### Risk assessment
- LOW: Only `setupAnim` changes. Existing state transitions for bask/dig preserved.
- The `chainWave` call is replaced, not removed — no API deprecation risk.
- Vertical undulation uses the same `Math.sin()` functions but on X-axis instead of Y-axis.

---

## 2. 🐛 CAVE CENTIPEDE — Priority S #2

### Why current animation is weak (2/10)
- **30 bones** (2nd most in project) — enormous potential wasted.
- All leg pairs use identical `swing()`/`flap()` with only offset tuning. Creates "wind through grass" effect: legs move, but without coordination.
- Real centipedes use **metachronal wave**: each leg pair starts its swing slightly AFTER the pair behind it, creating a traveling wave from tail→head. The current code has all legs at identical frequency with different phase offsets — this is noise, not coordination.
- Antennae share identical animation parameters.
- Head segments exist but don't participate in steering.

### Biological identity that must appear
**Scolopendromorph centipede**: Metachronal leg wave (back→front propagation). Body undulates in horizontal S-curve through narrow tunnels. Antennae are independent sensory organs — each probes a different direction in darkness. Fangs (forcipules) articulate for venom injection. 21-23 body segments, each with one leg pair.

### New animation system design

```
METACHRONAL LEG WAVE:
  // Each leg pair has a phase based on its body position
  // Pair 1 (front) → phase 0.0
  // Pair N (back)  → phase (N-1) * 0.15
  // This creates wave traveling from back to front
  float waveBase = limbSwing * walkSpeed
  for each leg pair at index i:
    float pairPhase = i * 0.15
    float pairWave = sin(waveBase - pairPhase)  [back leads, front follows]
    leg[i].swing(walkSpeed, walkDegree, pairWave)
    leg[i].flap(walkSpeed, walkDegree * 0.4, pairWave + 1.5)
  
  // Body horizontal S-curve (follows leg wave)
  body.rotateAngleY += sin(waveBase) * walkDegree * 0.3 * limbSwingAmount

ANTENNA INDEPENDENT PROBING:
  // Left antenna: fast, wide sweep
  antenna_left.flap(0.3, 0.12, false, 0, 0, ageInTicks, 1)
  antenna_left.swing(0.25, 0.08, true, 1.5, 0, ageInTicks, 1)
  // Right antenna: slower, different direction
  antenna_right.flap(0.22, 0.09, true, 0.7, 0, ageInTicks, 1)
  antenna_right.swing(0.28, 0.06, false, 2.1, 0, ageInTicks, 1)

HEAD STEERING:
  head.rotateAngleY += sin(waveBase - 0.3) * walkDegree * 0.15 * limbSwingAmount
  head2.rotateAngleY += sin(waveBase - 0.15) * walkDegree * 0.1 * limbSwingAmount
```

### Files to modify
- `ModelCaveCentipede.java` — `setupAnim()` method
- No model changes needed

### Required animation states
- Walk (metachronal wave): ✗ REDESIGN
- Idle (antenna probing + body micro-sway): ✗ ENHANCE
- Attack (fang articulation): ✗ NEW (subtle fang open/close)
- Turn (body curve): ✗ NEW (body.rotateAngleY with turn direction)

### Risk assessment
- HIGH: 30 legs in the model — need to map leg names to indices for the phase loop
- Need to verify exact leg bone naming. The scan showed `leftLegBodyB`, `leftLegBodyF`, `rightLegBodyB2`, etc. — naming is complex.
- If legs cannot be mapped to an index pattern, fall back to explicit per-leg calls with calculated phases.

---

## 3. 🦠 ENDERIOPHAGE — Priority S #3

### Why current animation is weak (2/10)
- 6 tail appendages, all animated with IDENTICAL `flap()` + `walk()` + `swing()` calls. Only the offset value differs (-2, -2.5, -5).
- "Erratic twitching" and "parasitic organism" identity is entirely unexpressed.
- The creature is a bacteriophage-inspired design — has capsid (head), sheath (neck), and tail fibers. Real phages have ASYNCHRONOUS tail fiber movement — each fiber contracts independently to attach to host cells.
- Currently: 32 procedural calls, all perfectly synchronized.

### Biological/fictional identity that must appear
**Enderiophage**: Ender-parasite. Bacteriophage-inspired body: capsid head, contractile sheath, 6 tail fibers. Each tail fiber moves AUTONOMOUSLY with micro-twitches — searching for surface to attach. Sheath contracts to inject ender-DNA. Body drifts with erratic twitching. Eye scans independently.

### New animation system design

```
ASYMMETRIC TAIL FIBERS:
  // Each of 6 tail fibers gets UNIQUE frequency + amplitude
  // Left side and right side also differ for organic asymmetry
  // tailfront_left: 0.25Hz, amp 0.10 — fast probing
  this.flap(tailfront_left, 0.25, 0.10, true, -1, 0.5, ageInTicks, 1);
  this.walk(tailfront_left, 0.20, 0.06, false, -2, 0.15, ageInTicks, 1);
  // tailfront_right: 0.20Hz, amp 0.08 — slightly different
  this.flap(tailfront_right, 0.20, 0.08, false, -1.5, 0.4, ageInTicks, 1);
  this.walk(tailfront_right, 0.22, 0.05, true, -1.8, 0.12, ageInTicks, 1);
  // tailmid_left: 0.18Hz, amp 0.12 — wider, slower
  this.flap(tailmid_left, 0.18, 0.12, true, -2.5, 0.6, ageInTicks, 1);
  this.walk(tailmid_left, 0.16, 0.07, false, -3, 0.18, ageInTicks, 1);
  // tailmid_right: 0.22Hz, amp 0.09
  this.flap(tailmid_right, 0.22, 0.09, false, -2.2, 0.45, ageInTicks, 1);
  // tailback_left: 0.15Hz, amp 0.14 — slowest, widest
  this.flap(tailback_left, 0.15, 0.14, true, -3, 0.7, ageInTicks, 1);
  // tailback_right: 0.19Hz, amp 0.11
  this.flap(tailback_right, 0.19, 0.11, false, -2.8, 0.55, ageInTicks, 1);

ERRATIC BODY TWITCH:
  // Micro-twitch: rapid small movement at irregular intervals
  float twitch = sin(ageInTicks * 3.5) > 0.85 ? sin(ageInTicks * 3.5) * 0.04 : 0;
  body.rotationPointX += twitch;
  body.rotationPointZ += sin(ageInTicks * 2.8 + 1.2) > 0.9 ? 0.03 : 0;
  
  // Slow drift with occasional direction change
  body.rotateAngleY += sin(ageInTicks * 0.3) * 0.08;
  body.rotateAngleZ += sin(ageInTicks * 0.25 + 1.5) * 0.05;
```

### Files to modify
- `ModelEnderiophage.java` — `setupAnim()` only

### Required animation states
- Idle (asymmetric fiber flutter + body twitch): ✗ REDESIGN
- Walk/swim (capsid-leading drift): ✗ REDESIGN
- Attack (sheath contract + inject): ✗ ENHANCE (add visible sheath compression)
- Passenger (rides entity): ✓ EXISTS

### Risk assessment
- LOW: Only `setupAnim` changes. All bones exist and are already animated — just replacing identical calls with unique ones.

---

## 4. 🦇 SKREECHER — Priority S #4

### Why current animation is weak (2/10)
- 30 procedural calls, all generic `walk()`/`swing()` with identical speed/degree.
- "Sonic hunter" identity is 100% unexpressed. No jaw articulation. No body vibration. No shriek preparation posture.
- Arms get identical `walk()` calls — left and right are mirrors but with same amplitude.
- Legs get identical `walk()` calls.
- Only `clingProgress` transition shows any unique behavior (inverting to hang from ceiling).

### Biological/fictional identity that must appear
**Skreecher**: Cave-dwelling sonic predator. Hangs upside-down from ceiling. Mouth opens WIDE to emit ear-splitting shriek that stuns prey. Wing membranes wrap around body like a cloak. Large eyes for darkness. Bat-like but predatory.

### New animation system design

```
SONIC SHRIEK MECHANICS:
  // Upper jaw opens wide for shriek (uses existing upperJaw bone)
  float shriekOpen = entity.isShrieking() ? 0.4 + sin(ageInTicks * 8.0) * 0.1 : 0  [rapid jaw vibrato]
  upperJaw.rotateAngleX -= 0.5 + shriekOpen
  
  // Whole-body vibration during shriek
  float shriekVibrato = entity.isShrieking() ? sin(ageInTicks * 12.0) * 0.04 : 0
  body.rotationPointX += shriekVibrato
  head.rotationPointX += shriekVibrato * 1.5

WING-CLOAK MECHANICS:
  // Arms wrap around body (membrane creates cloak silhouette)
  // leftArmPivot rotates inward, rightArmPivot rotates outward at idle
  leftArmPivot.rotateAngleZ += 0.3  [wrap left]
  rightArmPivot.rotateAngleZ -= 0.3  [wrap right]
  // Wing-tip hands droop (bat-like)
  leftHand.rotateAngleX += 0.2
  rightHand.rotateAngleX += 0.2

GROUND WALK (bipedal bat-waddle):
  // Uses wing-arms as front legs (knuckle-walking)
  this.walk(leftArm, walkSpeed, walkDegree * 1.5, true, 0, 0.3, limbSwing, limbSwingAmount * groundSpeed)
  this.walk(rightArm, walkSpeed, walkDegree * 1.5, false, 0, -0.3, limbSwing, limbSwingAmount * groundSpeed)
  // Legs follow
  this.walk(leftLeg, walkSpeed, walkDegree, false, 0, 0.2, limbSwing, limbSwingAmount * groundSpeed)
  this.walk(rightLeg, walkSpeed, walkDegree, true, 0, -0.2, limbSwing, limbSwingAmount * groundSpeed)
  // Body sways side-to-side (unstable bipedal)
  body.rotationPointX += sin(limbSwing * walkSpeed) * walkDegree * 0.4 * limbSwingAmount * groundSpeed

CEILING HANG (enhanced):
  // Existing clingProgress is good — enhance with subtle body sway
  body.rotateAngleZ += sin(ageInTicks * 0.15) * 0.05 * clingProgress
  // Legs/toes grip ceiling
  leftFoot.rotateAngleX -= 0.3 * clingProgress  [toes curl around]
  rightFoot.rotateAngleX -= 0.3 * clingProgress

PREY-LOCATING HEAD SCAN:
  // Head tilts side-to-side listening for prey
  head.rotateAngleZ += sin(ageInTicks * 0.4) * 0.15
  // Eyes track independently (left/right scan different directions)
  leftEye.rotateAngleY += sin(ageInTicks * 0.35 + 1) * 0.1
  rightEye.rotateAngleY += sin(ageInTicks * 0.35 + 1.8) * 0.1
```

### Files to modify
- `ModelSkreecher.java` — `setupAnim()` only
- Need to check: is there an `isShrieking()` method in `EntitySkreecher`? If not, use the existing `clapProgress` variable (line 134: `clapProgress = entity.prevClapProgress + ...`)

### Required animation states
- Idle (wing-cloak + head scan): ✗ REDESIGN
- Ground walk (bat-knuckle-waddle): ✗ REDESIGN
- Ceiling hang (enhanced sway): ✗ ENHANCE
- Sonic shriek (jaw + body vibrato): ✗ NEW
- Clap (exists): ✓ EXISTS

### Risk assessment
- MEDIUM: Need to verify `isShrieking()` or equivalent entity method exists. If not, use `clapProgress` as proxy.
- Upper jaw articulation is safe — `upperJaw` bone exists and is already in hierarchy.

---

## 5. 🐝 TARANTULA HAWK — Priority S #5

### Why current animation is weak (3/10)
- Ground walk code is **structurally identical** to `ModelLeafcutterAnt.java` — same `offsetLeft = 2F` constant, same `swing(leg, speed, walkDegree, ...)` ×6 + `flap(leg, speed, walkDegree*0.8, ...)` ×6 pattern. Only the specific numeric values differ slightly.
- The `jerk` variable was recently connected to `wingPower` — but the wing beat is still `flap()` which produces SMOOTH sinusoidal motion. Real wasps have CHAOTIC, arrhythmic wing beats with irregular amplitude modulation.
- The IDENTITY claims "stinger drags tarantula to burrow" but the drag state (`dragProgress`) only rotates the head down — no backward-pulling leg mechanics.

### Biological identity that must appear
**Tarantula hawk wasp (Pepsis)**: Solitary spider hunter. Walks on ground searching for tarantula burrows — deliberate, stop-start searching, not marching in a line. Antennae TAP the ground (not just wave in air). Paralyzes tarantula with precise sting to nerve ganglion. Then drags the paralyzed spider BACKWARD into a burrow. Wings have iridescent blue-black flash with JERKY, arrhythmic beats — wings buzz, then pause, then buzz again.

### New animation system design

```
DIFFERENTIATED WALK (from LeafcutterAnt):
  // Tarantula hawk walks with STOP-START searching pattern
  // Not a continuous march — pauses to probe ground
  float searchPause = sin(limbSwing * walkSpeed * 0.5)  [slow cycle]
  float walkActive = searchPause > 0.2 ? 1.0 : 0.3  [only walk during active phase]
  
  // Legs move during active walk phase only (search-pause pattern)
  limbSwingAmount *= walkActive
  
  // Then normal tripedal gait (same structure as before but with walkActive modulation)
  // This creates the distinctive wasp search-walk

CHAOTIC WING BEATS (not sinusoidal):
  // Use multi-frequency noise for wing amplitude
  float buzz1 = abs(sin(ageInTicks * 8.0))       [primary buzz ~100Hz illusion]
  float buzz2 = abs(sin(ageInTicks * 11.3)) * 0.6 [second harmonic]
  float buzz3 = abs(sin(ageInTicks * 5.7)) * 0.3  [slow amplitude modulation]
  float chaosPower = (buzz1 + buzz2 + buzz3) / 1.9  [normalized chaotic amplitude]
  
  this.flap(wing_left, flySpeed * 8, flyDegree * chaosPower, true, 0, 0.1, ageInTicks, 1)
  this.flap(wing_right, flySpeed * 8, flyDegree * chaosPower, false, 0, 0.1, ageInTicks, 1)
  
  // Irregular micro-pauses (wings briefly stop vibrating)
  float microPause = sin(ageInTicks * 3.0 + 2) > 0.7 ? 0.5 : 1.0
  wing_left.rotateAngleZ *= microPause
  wing_right.rotateAngleZ *= microPause

DRAG MECHANICS:
  // When dragging prey, legs PULL backward
  float pullBack = dragProgress * 0.2
  this.walk(legback_left, 0.6, 0.4 * pullBack, false, 0, -0.3, limbSwing, limbSwingAmount)
  this.walk(legback_right, 0.6, 0.4 * pullBack, true, 0, 0.3, limbSwing, limbSwingAmount)
  // Abdomen curls forward (stinger ready)
  abdomen.rotateAngleX -= 0.4 * dragProgress
  // Body leans backward (pulling weight)
  body.rotateAngleX += 0.3 * dragProgress
```

### Files to modify
- `ModelTarantulaHawk.java` — `setupAnim()` only
- No model changes needed

### Required animation states
- Ground search-walk (stop-start): ✗ REDESIGN
- Flight (chaotic wing beats): ✗ REDESIGN
- Sting (abdomen curl): ✓ EXISTS
- Drag (backward pull): ✗ REDESIGN (currently just head-lower)
- Dig (burrow excavation): ✓ EXISTS

### Risk assessment
- LOW: All bones exist. The walk differentiation uses limbSwingAmount modulation — safe technique.

---

## 6. 🌌 COSMAW — Priority S #6

### Why current animation is weak (3/10)
- "Jaws open into cosmic rift" identity — but jaw has zero idle animation. Only state transitions (dry/grab/capture) move the mouth.
- Body undulation is a single `chainSwing` — pure sinusoidal. Void creatures should have irregular, alien motion.
- 23 calls — decent procedural density but nothing expresses "cosmic" identity.

### Cosmic/fictional identity that must appear
**Cosmaw**: Living void portal. Jaws open to reveal cosmic rift (dimensional energy). Body segments ripple with star-energy. Movement is NON-EUCLIDEAN — irregular, phase-shifted, not pure sine waves.

### New animation system design

```
VOID JAW — always slightly moving:
  // Micro-gaping: jaw slowly opens/closes at idle (breathing cosmic energy)
  float jawGape = sin(ageInTicks * 0.04 + 2) * 0.5 + 0.5  [0→1 slow cycle]
  // One jaw pair opens, one closes — asymmetrical alien feeding
  // (need to check which bones exist for jaws — likely topjaw/bottomjaw or similar)

IRREGULAR UNDULATION:
  // Replace single chainSwing with TWO chains at different frequencies
  // This creates non-repeating alien undulation
  AdvancedModelBox[] bodyChain = {segment1, segment2, segment3, ...}
  this.chainSwing(bodyChain, 0.08, 0.3, -3, ageInTicks, 1)   [primary wave]
  // Second chaing at incommensurate frequency creates irregular motion
  this.chainFlap(bodyChain, 0.05, 0.15, -2, ageInTicks, 1)    [Z-axis alien wave]
  
  // Body also slowly rotates (portal spin)
  root.rotateAngleY += sin(ageInTicks * 0.03) * 0.08
```

### Files to modify
- `ModelCosmaw.java` — `setupAnim()` only
- Need to identify jaw bone names in the model

### Required animation states
- Idle (jaw micro-gape + body irregular undulation): ✗ NEW
- Swim (chainSwing + chainFlap dual): ✗ ENHANCE
- Attack/grab (jaw WIDE + body lunge): ✓ EXISTS (state transitions)

### Risk assessment
- LOW: chainFlap addition is simple. Jaw animation depends on bone naming — needs verification.

---

## 7. 🐟 CATFISH (3 sizes) — Priority S #7

### Why current animation is weak (2/10)
- All three sizes use IDENTICAL animation code. Same frequencies, same amplitudes, same everything.
- 25-29 calls per size — but identical across sizes.
- "Barbel drag" identity is a simple `swing()` — not friction-based substrate drag.
- Subcarangiform wave should differ between sizes: small catfish = faster/tighter, large = slower/wider.

### Biological identity that must appear
**Catfish**: Whiskered bottom-feeder. Barbels drag along substrate — friction creates irregular drag motion, not smooth sine wave. Subcarangiform swimming (anterior body stiff, posterior undulates). Small = quick, nervous. Medium = moderate. Large = slow, powerful, deliberate.

### New animation system design

```
SIZE-DIFFERENTIATED SWIMMING:
  Large (ModelCatfishLarge.java):
    float swimSpeed = 0.3, swimDegree = 0.4   [slow, powerful strokes]
    float barbelDrag = sin(ageInTicks * 0.25) * 0.15  [slow heavy drag]
    
  Medium (ModelCatfishMedium.java):
    float swimSpeed = 0.5, swimDegree = 0.35  [moderate]
    float barbelDrag = sin(ageInTicks * 0.35) * 0.12
    
  Small (ModelCatfishSmall.java):
    float swimSpeed = 0.7, swimDegree = 0.25  [quick, nervous, tight]
    float barbelDrag = sin(ageInTicks * 0.5) * 0.08

BARBEL SUBSTRATE DRAG (replaces simple swing):
  // Simulates friction: barbels don't swing freely — they drag with resistance
  // Use asymmetric motion: slow drag forward, quick flick back
  float dragRaw = sin(ageInTicks * barbelFreq)
  float dragMotion = dragRaw < 0 ? dragRaw * 0.3 : dragRaw * 0.8  [asymmetric]
  left_BigWhisker.rotateAngleZ += dragMotion
  right_BigWhisker.rotateAngleZ -= dragMotion * 0.9  [slightly different]
  // Small whiskers move faster (more sensitive)
  left_SmallWhisker.rotateAngleZ += dragMotion * 1.3
  right_SmallWhisker.rotateAngleZ -= dragMotion * 1.2
```

### Files to modify
- `ModelCatfishLarge.java` — `setupAnim()` values only
- `ModelCatfishMedium.java` — `setupAnim()` values only
- `ModelCatfishSmall.java` — `setupAnim()` values only

### Required animation states
- Swim (size-differentiated): ✗ REDESIGN
- Barbel drag (asymmetric friction): ✗ REDESIGN
- Idle (bottom-resting): ✗ NEW (subtle fin + barbel twitch)

### Risk assessment
- LOW: Three separate files — changing only speed/degree values and barbel mechanics.

---

## 8-10: REMAINING PRIORITY S

### 🐉 Laviathan
**Problem**: "Lava serpent" — pure sinusoidal undulation. No heat-shimmer, no convection ripple.
**Solution**: Add second `chainFlap` at incommensurate frequency for irregular "heat" motion. Jaw micro-gape for heat exhaust. Body segments use `setScale` for shimmer illusion.
**Difficulty**: LOW | **Time**: 20 min

### 🦅 SoulVulture
**Problem**: "Spectral carcass scavenger" — generic bird flight. No vulture-specific behavior.
**Solution**: Add thermal-circling (slow body bank + wing spread). Head scanning for carrion (independent head rotation from flight path). Wing-finger splay at idle.
**Difficulty**: LOW | **Time**: 20 min

### 🦐 Triops
**Problem**: "Upside-down swimming living fossil" — generic lateral undulation. No inverted posture.
**Solution**: Invert body pitch (rotateAngleX ≈ 160 degrees) so creature swims upside-down. Continuous leg ripple (all legs in wave, not discrete steps). Tail flipper steering.
**Difficulty**: LOW | **Time**: 20 min

---

## PRIORITY A SUMMARY (15 creatures)

See `AAA_ANIMATION_ANALYSIS.md` for full details. Key patterns:

| Pattern | Affected | Fix |
|---|---|---|
| Generic walk/swing/flap with identical params | Cockroach, Terrapin, SugarGlider, RainFrog, Bunfungus, Lobster | Unique frequency per limb |
| No visible breathing (rotationPointY only) | RockyRoller, Mudskipper, FlyingFish, CrimsonMosquito, Fly | Add `body.setScale(1, 1+breath*0.02, 1)` |
| Copied walk pattern from another creature | LeafcutterAnt (copied by TarantulaHawk, now fixed) | Verify uniqueness |
| Missing signature behavior | Platypus (bill-sweep), Skelewag (vertebra rattle), MantisShrimp (punch cocking) | Add creature-specific idle |
| Model limited | Fly (6 bones), Stradpole (5 bones) | Accept ceiling |

---

## SHARED CLASSES / CROSS-CUTTING CONCERNS

### Universal improvements (apply to ALL Priority A+B):

1. **Visible breathing**: Add `body.setScale(1.0, 1.0+breath*0.02, 1.0)` to every creature that currently only does `body.rotationPointY += breath * X`. This alone improves 50+ creatures.

2. **Secondary motion**: Every creature should have at least ONE of: ear flick, whisker twitch, antenna probe, snout wiggle, feather ripple, or tail tip flick.

3. **Head stabilization for birds**: Every bird during ground locomotion needs `head.rotateAngleX -= bodyBob * factor` to stabilize the head.

### Pattern to NEVER repeat:
- `walk()` on all limbs with identical speed/degree (the original GiantSquid problem)
- Copying gait architecture between species (the TarantulaHawk/LeafcutterAnt problem)
- `Mth.cos(ageInTicks * frequency)` as the ONLY secondary motion

---

## RISK MATRIX

| Risk | Severity | Mitigation |
|---|---|---|
| Bone names don't match expected pattern (CaveCentipede) | HIGH | Read constructor first, map names to indices |
| Entity method doesn't exist (Skreecher `isShrieking`) | MEDIUM | Use existing `clapProgress` as proxy |
| chainFlap on Z-axis may look wrong on vertically-oriented bones | LOW | Test visually, use smaller amplitude |
| Three Catfish files diverge from shared code pattern | LOW | Keep structure identical, only change values |
