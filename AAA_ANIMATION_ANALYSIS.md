# AAA_ANIMATION_ANALYSIS.md
## Alex's Mobs — Comprehensive Animation Gap Analysis (Phase 2)
**Lead Animation Director Review** | **Date**: 2026-07-20 | **Scope**: 89 creatures

---

## METHODOLOGY

Every remaining creature's model file was read in full. Analysis covers:

- **Biomechanical accuracy**: Does the animation match how the real animal moves?
- **Procedural call quality**: Are `walk()`, `swing()`, `flap()`, `bob()`, `chainSwing()`, `chainFlap()`, `chainWave()` used with species-appropriate parameters?
- **Gait architecture**: Is there explicit tripedal/diagonal/lateral/pace/hopping gait logic — or just generic sine waves?
- **Secondary motion**: Ears, whiskers, antennae, tail independent flick, feather ripple, fin flutter, eye movement — do they exist and are they unique?
- **Head stabilization**: Birds and fast-moving creatures MUST stabilize the head during locomotion.
- **Breathing visibility**: Is `setScale()` used to make breathing visible, or is it just an invisible `rotationPointY` offset?
- **Weight transfer**: Does the creature's center of gravity shift correctly during locomotion?
- **State transitions**: Are `progressRotation()`/`progressPosition()` smooth, physically plausible, and complete for all states?
- **Uniqueness**: Can an experienced player identify the species from animation alone (without textures)?

### Scoring Scale
| Score | Meaning |
|-------|---------|
| 1-2/10 | Barely functional — generic formulas, no identity |
| 3-4/10 | Basic mechanics present but generic; could be any creature |
| 5-6/10 | Good foundation, some unique identity, needs targeted polish |
| 7-8/10 | Strong identity, most mechanics correct, near-professional |
| 9-10/10 | AAA quality — indistinguishable from hand-keyed animation |

### Ceiling Score
The **Potential Maximum Score** is bounded by the model's bone count and hierarchy. A creature with 5 bones cannot reach 8/10 because there aren't enough articulating parts to carry complex animation identity.

---

## EXECUTIVE SUMMARY

| Category | Count | Status |
|---|---|---|
| Completed (Priority S) | 10 | FULLY REDESIGNED — at model ceiling |
| Priority A | 15 | MAJOR IMPROVEMENTS NEEDED |
| Priority B | 15 | MODERATE POLISH NEEDED |
| Priority C | 49 | AT CEILING — no further work needed |

---

## PRIORITY A — MAJOR IMPROVEMENTS REQUIRED (15 creatures)

These creatures have foundations but significant gaps in uniqueness, biomechanics, or secondary motion. Each needs substantial work to reach professional quality.

---

### A-1: Cockroach — Overall: 4/10 | Potential: 7/10

**Model**: 14 bones: root, abdomen, left_leg_front/mid/back, right_leg_front/mid/back, left_wing, right_wing, neck, head, left_antenna, right_antenna

**What's good**:
- Tripedal gait framework exists with correct phase offsets (L1+R2+L3 vs R1+L2+R3)
- Abdominal spiracle breathing via `setScale()` — visible and species-appropriate
- Antenna dual-axis probing (swing + walk + flap on each antenna)
- Body rock during walk simulates weight transfer between tripods
- Wing twitch mechanic (threshold-triggered at idle)
- Dance state (La Cucaracha) is creative and functional
- Flight leg-tuck is correct insect posture

**What's weak**:
- All 6 legs use identical `walkSpeed=1.25F, walkDegree=0.45F` — only `invert` and `offset` differentiate them. Real cockroach legs have different stride lengths (front legs shorter, rear legs longer)
- The body bob during walk is generic: `this.bob(abdomen, walkSpeed, walkDegree * 2.0F, true, limbSwing, limbSwingAmount)`. No explicit COM (center of mass) calculation
- No rapid direction-change mechanic (cockroaches are famous for unpredictable zigzag sprints)
- Flight wing flap uses `this.swing()` not `this.flap()` — biomechanics mismatch. Insect wings flap vertically, not swing laterally
- No escape-dash burst speed differentiation from normal walk
- Antenna wall-following not expressed (cockroaches use antennae to follow walls in darkness)

**What's missing completely**:
- **Rapid scuttle burst**: Cockroaches can accelerate to 50 body-lengths/second. No speed-dependent gait change exists
- **Wall-following antenna behavior**: Antennae should bias toward one side when near a wall
- **Wing deployment sequence**: Real cockroaches raise wing covers (tegmina) first, then unfold hindwings — current code just swings wings open
- **Cercal escape response**: Cockroaches have wind-sensitive cerci that trigger instant escape — no startle animation

**Biomechanics violations**:
- Wing flight uses `swing()` (horizontal axis) instead of `flap()` (vertical axis) — insects flap in the vertical plane
- All 6 legs have equal amplitude; real cockroach hind legs produce more power

**Why A-tier, not B-tier**: Good foundation but wing biomechanics are wrong and the most distinctive cockroach behaviors (escape dash, wall-following) are absent.

**Estimated work**: 1.5 hours — fix wing axis, add dash gait, wall-following antenna bias

---

### A-2: Terrapin — Overall: 3/10 | Potential: 5/10

**Model**: 13 bones: root, body, head, tail, shell, left_arm/hand, right_arm/hand, left_leg/foot, right_leg/foot

**What's good**:
- Head retraction state via `retreatProgress` with proper position/rotation interpolation
- Swim state with limb repositioning (arms/legs rotate outward for paddle stroke)
- Shell and body are separate bones — allows shell-rock while body moves independently
- Breathing via `rotationPointY` on body and shell
- Spin state exists with proper body rotation + bob

**What's weak**:
- Ground walk: all 4 limbs use identical `walk(walkSpeed, walkDegree, ...)` with only invert/offset differentiation. No diagonal gait architecture
- Swim: all 4 limbs use identical `flap(swimSpeed, swimDegree * 0.2F, ...)` — all limbs flap at same speed/amplitude. Real turtles use front limbs as primary paddles, rear limbs as rudders
- The `retreatProgress` limb positions use identical angles for arms and legs (both `Maths.rad(-90)` / `Maths.rad(90)`) — front and rear limbs should retract differently
- Head tracking disabled during retreat and spin — correct but head should have slow scanning at idle
- No shell-rock during walking (turtles visibly rock side-to-side with each step)
- Breathing is invisible (`rotationPointY` only, no `setScale`)

**What's missing completely**:
- **Head-neck extension for grazing**: Terrapins extend their neck to graze on aquatic plants — no grazing state exists
- **Asymmetric paddle stroke**: Front limbs should do 70% of the swimming work, rear limbs 30%
- **Shell-rock counter-rotation**: As each leg steps forward, the shell should rock to the opposite side
- **Visible breathing**: Shell/body should slightly expand with breath via `setScale()`
- **Basking posture**: Real terrapins bask with limbs extended to absorb heat — no basking state

**Biomechanics violations**:
- All 4 limbs doing identical work in both walk and swim — violates turtle anatomy
- No head-bobbing during walking (turtles bob their heads with each step)

**Ceiling explanation**: 13 bones with independent shell and limbs. The model supports differentiated front/rear limb animation. Ceiling of 5/10 is limited by the simple limb structure (no finger/toe bones, no neck vertebrae).

**Why A-tier**: 4 identical limbs doing identical work. No shell-rock. No grazing. This is a generic quadruped with a shell, not a terrapin.

**Estimated work**: 1.0 hour — differentiate front/rear limbs, add shell-rock, add grazing state, add visible breathing

---

### A-3: SugarGlider — Overall: 3/10 | Potential: 6/10

**Model**: 10 bones: root, body, leftArm, rightArm, leftLeg, rightLeg, tail, head, leftEar, rightEar

**What's good**:
- Glide progress state with proper body rotation and limb spread
- Sit progress state with body curling
- Forage progress state with head lowering
- Ears have independent flap
- Glide membrane ripple when gliding (`rotateAngleZ` modulation during glide)
- Ground walk uses `swing()` on all 4 limbs with walk speed/degree

**What's weak**:
- Ground walk: all 4 limbs use identical `swing(walkSpeed, walkDegree, false, 1.5F, -0.2F, ...)` — only invert differentiates left from right. Real sugar gliders have different front/rear stride lengths
- The tail during walk is just `this.swing(tail, walkSpeed, walkDegree, true, 0F, 0F, ...)` — a generic pendulum. Real sugar gliders use their tail as an active counterbalance, curling and uncurling with each step
- Glide state transitions are rotation-only — no membrane deployment sequence (patagium should unfurl from wrist to ankle)
- Breathing is invisible (`rotationPointY` only, no `setScale`)
- No vertical climbing state (sugar gliders are arboreal and climb tree trunks vertically)
- Eye size is large (big eyes are accurate) but no independent eye movement or blink

**What's missing completely**:
- **Tree-trunk vertical climb**: Sugar gliders climb bark vertically with all 4 limbs gripping — this is their primary non-gliding locomotion
- **Membrane deployment sequence**: The patagium should unfurl in sequence: wrist extends → membrane stretches → ankle follows
- **Tail counterbalance during climb**: Tail should actively curl and shift to maintain balance on vertical surfaces
- **Head stabilization during glide**: Head should stay level even as body banks
- **Landing impact absorption**: When landing after a glide, limbs should compress to absorb impact

**Biomechanics violations**:
- Tail is a passive pendulum during walk — should be an active, curling counterbalance
- Glide transitions are instantaneous rotations — should be a progressive unfurling

**Why A-tier**: The glide state is the creature's identity but it's implemented as rotation-only with no membrane deployment. The ground walk is fully generic.

**Estimated work**: 1.5 hours — add vertical climb, membrane deployment sequence, active tail counterbalance, landing compression

---

### A-4: RainFrog — Overall: 3/10 | Potential: 5/10

**Model**: 9 bones: root, body, tongue, left_arm, right_arm, left_leg, right_leg, left_eye, right_eye

**What's good**:
- Burrow progress with body descent (body sinks into ground)
- Dance progress with rhythmic body swing
- Attack progress with tongue extension
- Stance progress (defensive inflation) with body scale increase
- Eye blink via position offset
- Leg-ground-contact compensation (rotationPointY/Z adjust with walk)
- Breathing via `setScale()` — visible body squash/stretch
- Eye movement synced with breathing

**What's weak**:
- Walk: all 4 limbs use identical `walk(walkSpeed, walkDegree, ...)` with only invert differentiating left from right
- The body walk is generic: `this.flap(body, walkSpeed, walkDegree * 0.35F, ...)` and `this.swing(body, walkSpeed, walkDegree * 0.35F, ...)` — no explicit frog-hop mechanics
- Burrowing is just body lowering — no backward-kicking leg scrape that real rain frogs use to dig
- The squeak identity is told not shown — no body inflation corresponding to vocalization
- No arm/leg differentiation: rain frogs have tiny stubby arms and slightly longer back legs, but both get identical animation parameters

**What's missing completely**:
- **Backward burrow**: Rain frogs burrow BACKWARD into soil using their hind legs to scrape — the burrow animation should involve rear-leg digging, not just body sinking
- **Squeak inflation**: When the frog squeaks, the throat/body should visibly inflate like a balloon — this is THE rain frog signature
- **Grumpy head-tilt**: Rain frogs are famous for their grumpy expression — head should periodically tilt sideways
- **Lateral hop**: Rain frogs don't walk — they hop short distances sideways

**Biomechanics violations**:
- Rain frogs don't walk — they hop. The walk code should be replaced with hop mechanics
- Burrowing is not just sinking — it's active rear-leg excavation

**Ceiling explanation**: 9 bones is limited. No separate throat sac bone. The model can't articulate individual toes or a highly expressive face. Maximum 5/10 is realistic for this model.

**Why A-tier**: The creature's two signature behaviors (backward burrow and squeak inflation) are not expressed in procedural animation.

**Estimated work**: 1.0 hour — replace walk with hop, add burrow-leg-scrape, add squeak inflation, add grumpy head-tilt

---

### A-5: RockyRoller — Overall: 3/10 | Potential: 5/10

**Model**: 14 bones: root, body, HSpikes_r1, HSpikes_r2, VSpikes_r1-r4, tail, head, left_arm, right_arm, left_leg, right_leg

**What's good**:
- Roll/unroll state with body position/rotation interpolation
- Rolling momentum via `body.rotateAngleX = timeRolling * 0.2F`
- Client-side roll tracking (`entity.clientRoll`)
- Spike flap during roll (VSpikes_r1/r2 pulse while rolling)
- Breathing visible via `body.setScale()`
- Tail idle swing and walk sway

**What's weak**:
- Walk: only left_leg and right_leg get `walk()` — arms get only idle bob. Real isopods use 7 pairs of legs; this model has 2 pairs. Arms should participate in walking
- The spike animation during roll is minimal: 2 of 6 spike bones get a tiny flap. All spikes should ripple when rolling for protection display
- No uncurl segment ripple — when pillbugs uncurl, their segments ripple one after another. Currently uncurling is just a progressRotation
- Body bob during walk: `this.bob(body, walkSpeed * 0.8F, walkDegree, true, limbSwing, limbSwingAmount * walkProgress * 0.2F)` — the `walkProgress * 0.2F` multiplier means the bob is almost invisible
- Head tracking uses raw angle division instead of `faceTarget()`

**What's missing completely**:
- **Segment uncurl ripple**: When uncurling, body should ripple from head to tail
- **Spike protection display**: All 6 spike bones should bristle outward when threatened
- **Armored-ball idle rocking**: When curled up, the ball should rock slightly from momentum
- **Antenna-like head probing**: Head should scan environment actively

**Biomechanics violations**:
- Arms (front legs) don't participate in walking — isopods use all legs
- Uncurling is instant — should be a progressive segment wave

**Why A-tier**: Good rolling mechanic but the uncurling has no segment ripple, and walking ignores the front limbs. Spike animation is nearly absent.

**Estimated work**: 1.0 hour — add arm walk participation, add uncurl ripple, enhance spike display during roll

---

### A-6: Bunfungus — Overall: 4/10 | Potential: 6/10

**Model**: 18 bones: root, body, belly, tail, head, left_brow, right_brow, shroom_cap, left_ear, right_ear, snout, snout_r1, left_arm, right_arm, left_leg, left_foot, right_leg, right_foot

**What's good**:
- ModelAnimator sequences for EAT, BELLY, SLAM — professional keyframe animation
- Sleep state with full-body position/rotation transitions
- Jump and fall progress states with body pitch and limb repositioning
- Interested progress with head tilt and eyebrow shift
- Limb swing modifier clamped at 0.38F for controlled walk
- Breathing visible via `belly.setScale()` — belly expands with breath
- Ear dual-axis animation (flap + swing) at idle
- Snout micro-flap for sniffing

**What's weak**:
- The walk is generic quadruped: body flap+swing, feet swing, head flap+swing, tail flap+swing — no mushroom-specific gait
- Mushroom cap (`shroom_cap`) is attached to head but gets NO independent animation — it should wobble independently from the head like a real mushroom cap
- The `walkMod` reduces all walk motion during jump/fall — this creates a jarring stop when jumping
- No spore-puff mechanic — Bunfungus is a mushroom hybrid but has no spore release animation
- The fungal spore identity is told not shown — no body puff, no spore particle timing sync
- Leg-ground-contact uses the generic `Math.sin()` formula copied from other quadrupeds

**What's missing completely**:
- **Mushroom cap wobble**: The cap should have its own inertia — wobbling with a slight delay after the head moves (follow-through)
- **Spore puff body pulse**: When releasing spores, the body should expand and contract
- **Fungal growth ripple**: The mushroom cap could periodically pulse/grow slightly
- **Root-like foot anchoring**: Fungal creatures could root into the ground when still

**Biomechanics violations**:
- Mushroom cap is rigidly attached to head with no independent motion — real mushroom caps wobble
- No follow-through on ears during walk — ears should drag behind head movement

**Why A-tier**: 18 bones but the mushroom cap (the creature's defining feature) is completely static. Walk is generic quadruped. No spore mechanics.

**Estimated work**: 1.5 hours — add mushroom cap inertia, add spore-puff body pulse, add ear follow-through, differentiate walk from standard quadruped

---

### A-7: Mudskipper — Overall: 5/10 | Potential: 7/10

**Model**: 8 bones: root, head, eyes, tail, dorsalFin, tailFin, leftFin, rightFin

**What's good**:
- Fin-walking concept is expressed: fins `flap()` + `swing()` on the ground
- `headUp` variable creates body lift during fin push — simulates mudskipper crutching locomotion
- Blink mechanic for periscope eyes
- Display state with dorsal fin spread
- Sit state with body curl
- Swimming uses chainSwing for body undulation
- Breathing visible via `head.setScale()`
- Fin-push body pitch: `head.rotateAngleX += landPush` — correct for mudskipper land locomotion
- Dorsal fin ripple at idle

**What's weak**:
- The fin-walk uses `flap()` and `swing()` on leftFin/rightFin — mudskippers don't flap their fins, they use them as CRUTCHES: plant fins, push body forward, lift fins, repeat. The current animation makes fins look like wings
- `chainSwing` is used for swimming undulation which is correct, but the amplitude is uniform across all segments — real eel-like swimmers have increasing amplitude toward the tail
- Eye periscope scanning is not expressed — the `eyes` bone has blink but no independent eye rotation tracking
- The dorsal fin display is triggered by `displayProgress` but has no threat-posture body language to accompany it
- Tail fin sculling (mudskippers use their tail to skip across mud) is not expressed
- 8 bones limits ceiling significantly

**What's missing completely**:
- **Crutching walk cycle**: Fins plant → body pushes forward → fins lift → repeat. This is fundamentally different from `flap()` which oscillates continuously
- **Mud-skip body flick**: Mudskippers flick their body to skip across mud — this is in the name and not in the animation
- **Periscope eye scanning**: Eyes on top of head should rotate independently to scan for predators
- **Threat display with dorsal/pectoral fin spread**: When displaying, the fish should raise its body and spread all fins

**Biomechanics violations**:
- `flap()` on pectoral fins for walking — fins are used as crutches, not flapping wings
- No tail involvement in land locomotion (mudskippers use tail flicks for mud-skipping)

**Why A-tier (barely)**: The fin-walk concept is present but the implementation (flap instead of crutch mechanics) prevents it from reaching professional quality. 8 bones limits potential but 7/10 is achievable with correct crutching mechanics.

**Estimated work**: 1.5 hours — replace fin flap with crutch cycle, add periscope eye scanning, add mud-skip tail flick, add threat display

---

### A-8: FlyingFish — Overall: 2/10 | Potential: 5/10

**Model**: 8 bones: root, body, tail, tail_fin, left_pectoralFin, right_pectoralFin, left_pelvicFin, right_pelvicFin

**What's good**:
- Glide state: pectoral fins rotate to 85 deg spread for gliding — correct aerodynamics
- Pelvic fins also spread during glide
- Body banks in glide: `body.rotateAngleZ += sin(ageInTicks * 0.3F) * 0.1F * flyProgress`
- Swim state: body bob + tail swing for subcarangiform swimming

**What's weak**:
- Only 6 procedural calls TOTAL — the entire creature has fewer animation operations than a single limb on many other creatures
- Glide: fins spread but there's NO fin-flutter during glide. Real flying fish vibrate their pectoral fins at high frequency during flight
- No takeoff sequence: real flying fish build speed underwater, break the surface, then spread fins. Current code just transitions instantly
- No tail sculling: flying fish use their tail fin (lower lobe longer) to scull the water surface for extra thrust during takeoff — this is entirely absent
- Swim: only `bob(body)` and `swing(tail)` — no body undulation chain, no pectoral fin tuck
- Breathing is invisible (`rotationPointY` only, no `setScale`)

**What's missing completely**:
- **Takeoff sequence**: Underwater acceleration → surface break → tail scull → fin spread → launch
- **Pectoral fin flutter during glide**: Rapid vibration of extended fins for stability
- **Glide banking with head stabilization**: Body banks but head should stay level
- **Landing splash**: Impact when re-entering water
- **Subcarangiform body wave during swim**: Only tail swings, body remains rigid

**Biomechanics violations**:
- Swimming uses only tail swing — real fish swim with body undulation starting from head
- Glide has no fin vibration — real flying fish vibrate pectoral fins at ~50Hz during flight

**Why A-tier**: 8 bones and only 6 procedural calls. The creature's signature behavior (surface-to-air transition) is a single `progressRotation`. This is barely animated.

**Estimated work**: 1.5 hours — add takeoff sequence, add fin flutter during glide, add subcarangiform swim chain, add tail scull

---

### A-9: LeafcutterAnt — Overall: 4/10 | Potential: 6/10

**Model**: 15 bones: root, body, legfront/mid/back L+R (6), abdomen, head, leaf, leaf_r1, antenna L+R, fangs

**What's good**:
- Tripedal gait with explicit phase offsets: `offsetleft = 2F` creates alternating tripods
- Body rock from tripod alternation: `body.rotationPointX += tripodRock`
- Abdomen drag follow-through: abdomen trails behind body with delayed phase
- Antenna dual-axis probing (swing + walk + flap)
- Breathing visible via `abdomen.setScale()`
- ModelAnimator for bite attack
- Leaf fragment carried overhead (visual identity)

**What's weak**:
- The tripedal gait uses `offsetleft = 2F` — this is the SAME constant as the old TarantulaHawk code. While the gait pattern itself is correct for both ants, the identical constant is suspicious
- Leg parameters: `walkSpeed=1F, walkDegree=1F` for all 6 legs — only phase offsets differentiate them. Real ants have different stride lengths per leg pair
- The walk body bob: `this.bob(body, walkSpeed * 2F, walkDegree * -0.6F, ...)` — the `* 2F` speed makes body bob at 2x the walk frequency, which creates a jittery look
- Antenna probing uses `idleSpeed=0.25F, idleDegree=0.25F` — very small amplitude. Real ant antennae are constantly active with large sweeps
- No pheromone-trail following behavior: ants follow chemical trails with their antennae dragging on the ground
- The leaf that the ant carries is completely static — it should sway with the ant's movement

**What's missing completely**:
- **Pheromone trail head movement**: Antennae should tap/drag on the ground following chemical trails
- **Leaf sway inertia**: The carried leaf fragment should have its own physics — swaying with a delay after body movement (follow-through)
- **Mandible articulation at idle**: Fangs should have micro-movement, not just during bite animation
- **Speed-dependent gait change**: Ants switch from tripedal to quadrupedal gait at higher speeds

**Biomechanics violations**:
- Body bob at 2x walk speed creates unrealistic jitter
- All 6 legs have identical walk degree despite different mechanical roles

**Why A-tier**: The tripedal gait is basically correct but the identical offset constant with TarantulaHawk, the 2x bob speed jitter, and the static leaf fragment prevent professional quality.

**Estimated work**: 1.0 hour — fix bob frequency, differentiate leg degrees, add leaf inertia, add pheromone-trail antenna behavior

---

### A-10: CrimsonMosquito — Overall: 3/10 | Potential: 5/10

**Model**: 17 bones: root, body, wingL/R, legsL/R with legL1-3/legR1-3, tail, head, antennaL/R, mouth

**What's good**:
- Flight wing flap with base twist for realism
- Leg dangling during flight with independent flap rhythms
- Blood bloat: tail scales up with blood level
- Shooting state with head/mouth rotation
- Passenger/riding state with leg repositioning
- Antenna dual-axis probing (walk + flap)
- Breathing visible via `body.setScale()`
- Mouth pulse during riding state

**What's weak**:
- Flight wing flap uses `this.flap()` with a smooth sine wave — real mosquitoes have ERRATIC, unpredictable flight patterns. The wing animation should have chaos/jitter
- No zigzag flight path animation — mosquitoes are famous for their unpredictable zigzag approach
- The wing flap uses identical parameters for both wings: `flySpeed=0.5F, flyDegree=0.5F` for both — real dipterans (flies/mosquitoes) have halteres that beat at the same frequency but there should be slight asymmetry
- Leg dangling during flight is too uniform — all 6 legs get the same `flyDegree * 0.5F` amplitude
- No proboscis extension animation during blood-feeding — the mouth just rotates, doesn't extend
- Antenna probing amplitude is tiny: `walk(flySpeed, flyDegree * 0.15F, ...)` — barely visible

**What's missing completely**:
- **Erratic flight pattern**: Zigzag with sudden direction changes — this is THE mosquito signature
- **Proboscis extension sequence**: Mouth extends, pierces, retracts
- **High-pitch wing whine body vibration**: Mosquito wing beats at ~600Hz should cause visible body vibration
- **Landing approach deceleration**: Mosquitoes slow down and hover before landing

**Biomechanics violations**:
- Smooth sinusoidal wing flap — real mosquitoes have chaotic flight
- Legs dangle passively during flight — real mosquitoes actively position their legs as stabilizers

**Why A-tier**: The most distinctive mosquito behavior (erratic flight + zigzag) is absent. Wing animation is generic smooth flap instead of chaotic.

**Estimated work**: 1.5 hours — add erratic flight chaos, add proboscis extension, add zigzag position modulation, add body vibration

---

### A-11: Fly — Overall: 2/10 | Potential: 4/10

**Model**: 6 bones: root, body, legs, left_wing, right_wing, mouth

**What's good**:
- Ground state differentiation: wings fold when on ground
- Wing micro-twitch at idle on ground (threshold-triggered)
- Breathing visible via `body.setScale()`
- Mouth walk/flap for proboscis movement
- Compact, efficient code

**What's weak**:
- 6 bones is extremely limiting — the entire legs are a single bone ("legs"), so individual leg articulation is impossible
- Flight wing flap: `this.flap(left_wing, flySp*1.3F, flyDg, true, 0, 0.2F, ageInTicks, 1)` — generic sinusoidal flap, no chaos
- No leg-rubbing animation (flies famously rub their front legs together — this is a signature behavior)
- The mouth animation is just a generic walk/flap — no proboscis extension
- No erratic flight — same issue as CrimsonMosquito
- Ground walk is just `swing(legs, ...)` — the entire leg assembly swings as one unit

**What's missing completely**:
- **Leg-rubbing behavior**: Front legs rubbing together — iconic fly behavior
- **Erratic landing/takeoff**: Flies don't take off smoothly — they launch suddenly
- **Wing buzz body vibration**: High-frequency wing beat should vibrate body
- **Proboscis extension**: Mouth should extend to feed

**Ceiling explanation**: 6 bones with a single "legs" bone. Individual leg articulation is physically impossible. The ceiling is 4/10 because there simply aren't enough bones to express complex behavior. The model hierarchy is the limiting factor, not the animation code.

**Why A-tier**: Despite the low ceiling, the current implementation doesn't use the available bones to their fullest. Leg-rubbing and proboscis extension could be done with the existing bones but are absent.

**Estimated work**: 0.5 hours — add leg-rubbing, proboscis extension, erratic flight chaos (limited by model)

---

### A-12: Platypus — Overall: 3/10 | Potential: 5/10

**Model**: 10 bones: root, body, head, beak, fedora, arm_left, arm_right, leg_left, leg_right, tail

**What's good**:
- Swim state with webbed-foot paddle: limbs rotate outward for swimming
- Dig state with body pitch and arm digging motion
- Electro-sensing head sway when `isSensing()` or `isSensingVisual()`
- Fedora hat bone — creative visual identity
- State transitions: swim, dig, sense all have proper progressPosition/Rotation

**What's weak**:
- Ground walk: all 4 limbs use `swing()` + `flap()` with identical `walkSpeed=1F, walkDegree=0.5F` — only `invert` differentiates left from right. Front and rear limbs have different anatomy (webbed feet vs digging claws) but identical animation
- Swim: all 4 limbs use `flap(swimSpeed, swimDegree * 0.9F, ...)` — identical parameters. Real platypus uses front webbed feet for 90% of propulsion, rear feet for steering
- The electro-sensing head sway is `swing(head, swimSpeed, swimDegree * 0.25F, ...)` when sensing — this is just a swing, not a side-to-side bill sweep that real platypus use for electroreception
- Breathing is completely ABSENT — no breath variable, no `setScale`, no `rotationPointY` breath
- The beak is completely static at idle — no bill articulation
- Tail fat-storage — the tail is a bone but has no independent jiggle or drag physics

**What's missing completely**:
- **Bill-sweep for electroreception**: Platypus sweeps its bill side-to-side like a metal detector to sense electric fields from prey
- **Breathing**: Completely absent — every living creature needs visible breathing
- **Webbed-feet differentiation from front claws**: Front feet are webbed (paddle), rear have claws (digging/steering) — animation should reflect this
- **Tail fat-storage physics**: The tail should have its own inertia/jiggle as it stores fat
- **Egg-laying posture**: Unique monotreme behavior — could be a state

**Biomechanics violations**:
- Front and rear limbs animated identically despite different anatomy
- Bill sensing uses head swing instead of bill sweep
- No breathing at all

**Why A-tier**: Missing breathing entirely. Bill-sweep (the signature platypus behavior) is not expressed. Front/rear limbs are animated identically despite different anatomy.

**Estimated work**: 1.0 hour — add breathing, differentiate front/rear limb animation, add bill-sweep, add tail inertia

---

### A-13: Skelewag — Overall: 3/10 | Potential: 5/10

**Model**: 8 bones: root, body, head, flag, left_fin, right_fin, tail, tail_fin

**What's good**:
- ModelAnimator sequences for STAB and SLASH attacks — aggressive keyframe animation
- `chainSwing` on body→tail→tail_fin for serpentine swimming — correct approach
- Land state with body rotation (sideways orientation on land)
- Fall-apart death state: body parts separate on death
- Breathing via `body.rotationPointY += breath * 0.05F`
- Head pitch tracking for vertical orientation

**What's weak**:
- "Vertebrae rattle" identity is told not shown — the body is a single bone, so individual vertebrae can't rattle. However, the `body` could have a micro-vibration to simulate rattle
- Swimming uses `chainSwing(tailBoxes, swimSpeed, swimDegree, -2, limbSwing, limbSwingAmount)` — this is generic serpentine. The "death rattle" could add a secondary chaotic vibration on top
- The flag (sail on back) gets idle swing but no ripple/wave along its length
- Fins get identical `flap()` and `bob()` — left and right fins should have slight asymmetry
- Jaw clacking is only in ModelAnimator attacks — no idle jaw micro-clatter
- Breathing is invisible (rotationPointY only, no setScale)

**What's missing completely**:
- **Vertebrae death rattle**: Whole-body micro-vibration that intensifies when attacking or dying
- **Jaw micro-clatter at idle**: The jaw should have tiny periodic clacks even at rest
- **Flag sail ripple**: The flag should have a traveling wave along its length in water current
- **Visible breathing**: The body should slightly expand/contract with breath

**Biomechanics violations**:
- All fin movement is perfectly symmetrical — real fish have slight left/right asymmetries

**Why A-tier**: The "skeletal worm" identity is mostly in the textures, not the animation. The vertebrae rattle and jaw clack are missing from procedural code.

**Estimated work**: 0.75 hours — add body micro-vibration, add jaw idle clatter, add visible breathing, add flag ripple

---

### A-14: Lobster — Overall: 4/10 | Potential: 6/10

**Model**: 12 bones: root, body, antenna_left, antenna_right, arm_left, hand_left, arm_right, hand_right, tail, tail2, legs_left, legs_right

**What's good**:
- Scuttle body sway: `body.rotationPointX += scuttleRock; body.rotateAngleZ += scuttleRock * 0.2F` — lateral rocking correct for sideways scuttling
- `chainSwing` on tail→tail2 for uropod wave — crustacean tail fan
- Antenna dual-axis probing (walk + flap) with independent flutter
- Claw snap attack with arm/hand rotation
- Breathing visible via `body.setScale()`
- Leg walk with swing combo for multi-axis motion

**What's weak**:
- The scuttle rock uses `Mth.sin(limbSwing * walkSpeed * 0.5F)` — the 0.5F frequency multiplier means body rocks at half the leg frequency, which is correct for alternating gait, but the amplitude `walkDegree * 0.25F` is small
- Legs are a single bone each ("legs_left", "legs_right") — this means all 4 walking legs on each side move as one unit. Real lobsters have 4 pairs of walking legs that move semi-independently
- The tail fan (tail2) is a single flat bone — can't express individual uropod articulation
- Claw snap has no anticipation phase — the claw just moves to the snap position with no wind-up
- Antennae sweep but don't taste the substrate — lobsters use their antennae to chemically sense the environment
- `walkSpeed=3F` is very high — this makes the lobster skitter unnaturally fast compared to its size

**What's missing completely**:
- **Claw snap anticipation**: Wind-up (claws open wide) → pause → explosive snap
- **Tail-fan escape burst**: Rapid abdomen curl for backward escape swimming (caridoid escape reaction)
- **Sideways scuttle dominant over forward walk**: Lobsters primarily move sideways — the walk should bias lateral movement
- **Antenna chemical sensing**: Antennae should periodically dip to taste the substrate

**Biomechanics violations**:
- All 4 walking legs per side move as one unit — real lobsters have pereiopod pairs that move in metachronal rhythm
- Walk speed of 3F is too fast for a lobster — suggests 0.6F-0.9F would look more natural

**Why A-tier**: Claw snap has no anticipation. Tail-fan escape burst is absent. Legs are a single bone per side limiting individual articulation, but directional scuttle and substrate sensing can be improved.

**Estimated work**: 1.0 hour — add claw snap wind-up, add tail-fan escape, adjust walk speed, add antenna substrate-dip, bias toward sideways scuttle

---

### A-15: MantisShrimp — Overall: 4/10 | Potential: 6/10

**Model**: 16 bones: root, body, head, eye_left, eye_right, fist_left, fist_right, arm_left, arm_right, whisker_left, whisker_right, flapper_left, flapper_right, tail, legs_back, legs_front

**What's good**:
- Independently tracking eyes! `eye_left.rotateAngleX/Y` and `eye_right.rotateAngleX/Y` have separate pitch/yaw from entity data — this is the best eye system in the mod
- Punch progress with full arm+fist position/rotation interpolation
- Swim state with body walk/wave
- Whisker dual-axis idle (walk + swing)
- Flapper independent motion: left and right flappers move independently
- Breathing visible via `body.setScale()`
- Flapper ripple at idle

**What's weak**:
- The punch animation is a progressRotation from rest to strike — there's no wind-up/cocking phase. Mantis shrimp COCK their clubs back before striking (like a spring-loaded mechanism)
- The strike speed is constant progress — real mantis shrimp punch accelerates at 10,400g (fastest animal movement). There should be a SLOW cocking phase then INSTANT strike
- Leg animation: `legs_front` and `legs_back` are single bones each — all front walking legs move as one unit, all back legs as another
- Whisker animation uses identical `idleSpeed=0.1F, idleDegree=0.3F` for both left and right — should have independent rhythms
- Tail is a single bone — no uropod articulation
- Walk speed of 0.9F with `walkDegree=0.6F` is generic — mantis shrimp don't walk much, they're mostly stationary ambush predators

**What's missing completely**:
- **Punch cocking phase**: Slow club retraction → pause → explosive strike
- **Rainbow body shimmer**: Mantis shrimp are famous for their rainbow coloration — body could get scale pulses in different axes to simulate iridescence shimmer
- **Stalk-eye independent scan**: Already have the data pipes (leftEyePitch/Yaw, rightEyePitch/Yaw) — eyes should actively scan when not tracking
- **Club impact recoil**: After punching, the arm should recoil from the impact
- **Flapper threat display**: Mantis shrimp spread their raptorial appendages in threat displays

**Biomechanics violations**:
- Punch has no cocking phase — real mantis shrimp use latch-mediated spring actuation requiring a distinct cocking motion
- Constant punch speed — should be slow-cock then explosive release

**Why A-tier**: The eye tracking is excellent. But the "fastest punch in the ocean" (the creature's PRIMARY identity) has no cocking phase and constant speed. This undermines the entire creature's identity.

**Estimated work**: 1.5 hours — add punch cocking phase (2-stage: slow retract then instant strike), add rainbow shimmer, add eye scanning, add impact recoil

---

## PRIORITY B — MODERATE POLISH NEEDED (15 creatures)

These creatures have good foundations with unique identity. They need targeted improvements, not full redesigns.

---

### B-1: MimicOctopus — Overall: 6/10 | Potential: 7/10

**Model**: 19 bones: root, body, mantle, eyes, eye spikes (2), 8 arms, 4 creeper pivots

**What's good**:
- 8 arms each have UNIQUE idle frequencies: 0.07F, 0.09F, 0.11F, 0.06F, 0.08F, 0.07F, 0.10F, 0.08F — excellent arm independence
- Each arm also has unique flap frequencies for multi-axis curl
- Mantle siphon breathing visible via `setScale()`
- Eye spikes raise/lower independently
- Complete mimic state system (Guardian/Creeper/Pufferfish) with arm repositioning
- Creeper ground walk with creeperPivots

**What's weak**:
- Ground walk arms (non-CREEPER mode): all 8 arms get identical `idleDegree=0.02F` `idleSpeed=0.05F` — while this is for ground idle (not walking), it means ground-mode arms are all the same
- Water mimic arms (non-Guardian): all 8 arms use `degree*0.35F` with only speed variation (0.9, 1.1, 0.8, 1.2, 1.0, 0.85, 1.15, 0.95) — the amplitude is identical
- The ground walk body uses `walk(mantle,...)` and `swing(mantle,...)` — octopus ground locomotion uses arm walking not mantle swaying
- Eye spikes are binary (up/down) rather than continuously expressive

**What's missing completely**:
- **True octopus ground arm-walking**: Octopuses on the seafloor walk using individual arm steps, not body/mantle swaying like a quadruped
- **Arm suction cup flex**: Not possible without suction cup bones (model limitation)
- **Chromatophore color pulse**: Not in model scope (texture-based)

**Why B-tier**: The idle arm independence is excellent. Ground locomotion needs true arm-walking instead of body sway. Water mimic arms still have identical amplitude.

**Estimated work**: 0.75 hours — differentiate arm amplitudes in water mimic mode, improve ground locomotion to use arms for walking

---

### B-2: WarpedMosco — Overall: 6/10 | Potential: 7/10

**Model**: 31 bones: root, body, back, 4 legs with knees, chest, 4 wings, shoulders with spikes, arms with hands, head, antennae, proboscis — LARGE model

**What's good**:
- ModelAnimator for PUNCH_R, PUNCH_L, SLAM, SUCK, SPIT — extensive keyframe library
- Tripedal ground gait with alternating tripods expressed through walk parameter offsets
- 4-wing flight with phase-offset between top/bottom pairs
- Knee joint articulation (kneefront/back follow leg movement)
- Antenna independent probing with different frequencies (0.25F vs 0.2F)
- Proboscis micro-movement with dual-axis rhythm
- Arms have slow mantis-like reaching at idle
- Shoulder spikes ripple independently
- Body rock during ground walk from tripod alternation
- Flight turn states (flyLeftProgress/flyRightProgress) for banking

**What's weak**:
- Ground walk tripedal: only legfront and legback have explicit tripedal offsets. Knees follow but with uniform amplitude
- Wings in flight use `swing()` not `flap()` — insect wings beat in the vertical plane (flap axis), not lateral (swing axis)
- The proboscis animation uses direct `rotateAngleX` assignment instead of Citadel's procedural methods — can conflict with animation states
- Antenna amplitude is very small: `flap(antenna_left, 0.25F, 0.08F, ...)` = only 0.08 radians (~4.6 deg) of amplitude — barely visible
- Body scale breathing is small: `body.setScale(1.0F + breath * 0.015F, 1.0F, 1.0F)` — 1.5% scale change is nearly imperceptible

**What's missing completely**:
- **Leg tuck during flight**: Legs should tuck up close to body during flight (currently they just rotate slightly)
- **Proboscis extension for feeding**: The proboscis has no extend/retract mechanic beyond micro-movement

**Why B-tier**: Excellent foundation with tripedal gait, 4-wing flight, and extensive keyframe attacks. Wing axis fix (swing→flap) and leg tuck during flight would bring it near ceiling.

**Estimated work**: 0.5 hours — fix wing axis, add leg tuck during flight, increase breathing visibility, increase antenna amplitude

---

### B-3: Flutter — Overall: 6/10 | Potential: 7/10

**Model**: 12 bones: root, body, eyes, petals, 4 petals, 4 tiny limbs

**What's good**:
- 4 petals each have UNIQUE motion axes: front flap, back swing, right rotateAngleZ, left rotateAngleZ with different frequencies (0.28F vs 0.33F)
- Each petal also has secondary drift (flap/swing with different frequencies)
- Ethereal breathing with tri-axis scale: `body.setScale(1+breath*0.02, 1+breath*0.015, 1+breath*0.01)`
- Eye tracking with player camera (pupil follows look direction)
- Head shake state
- Ground delicate walk with bob
- Float drift: body tilts slowly while floating
- State transitions for shoot, fly, sit
- Baby scale handling

**What's weak**:
- Petal wings use `flap()` which is appropriate, but the 4 petals don't form a coherent wing beat cycle — they all beat independently with no phase relationship. Real butterflies have front and hind wings that overlap and coordinate
- The ground walk uses `walk(wkSp, wkDg*1.2F, ...)` on all 4 limbs with only invert differentiating them — limbs are tiny but could have slight differentiation
- Petal scale during tentacle state: `front_petal.setScale(1, ps, 1)` — only Y-axis, could be more organic
- Eye dots are flat — the eyes bone shows billboard-style eyes

**What's missing completely**:
- **Butterfly wing coordination**: Front and back petals on each side should have a phase relationship (front leads, back follows with slight delay)
- **Delicate landing**: The sit transition is instant — could have a gentle descent

**Why B-tier**: Excellent petal differentiation and ethereal feel. Wing coordination between front/back pairs would bring it to ceiling.

**Estimated work**: 0.5 hours — add front/back petal phase coordination, add gentle landing descent

---

### B-4: Endergrade — Overall: 4/10 | Potential: 5/10

**Model**: 12 bones: root, bodymain, bodyfront, head, mouth, tail, 6 legs

**What's good**:
- `chainWave` on body segments for caterpillar undulation — correct approach
- `chainWave` + `chainFlap` on legs for synchronized leg ripples — good multi-axis
- Mouth proboscis extension with bite progress
- Body pitch tracking (tardigrade pitch)
- Tail idle micro-movement when stationary
- Breathing visible via `bodymain.setScale()`

**What's weak**:
- All 6 legs share the same chain: `this.chainWave(legR, wkSp, wkDg, -1, limbSwing, limbSwingAmount)` — left and right legs have identical rhythm. Real caterpillars have a phase offset between left and right sides
- The leg chainFlap uses negative degree for left side: `this.chainFlap(legL, wkSp, -wkDg, 3, ...)` — the negative degree just inverts the motion, making left legs mirror right legs exactly. Should have phase offset instead
- Body chain uses only bodyfront, bodymain, tail — the head and mouth could be included for longer chain
- `chainWave` on body with degree 0.3F is very subtle — caterpillar undulation should be more visible
- Only 12 bones — limited model for expression

**What's missing completely**:
- **Left/right leg phase offset**: Real caterpillars have a metachronal wave where legs on opposite sides are 180 deg out of phase
- **Head scanning**: Caterpillars move their head side-to-side to sense the environment
- **Inchworm locomotion variant**: Some caterpillars use inchworm (loop) locomotion

**Ceiling explanation**: 12 bones with 6 simple legs. The model structure is appropriate but basic. Ceiling of 5/10 is realistic.

**Why B-tier**: The chainWave approach is correct but all legs are perfectly synchronized. Adding a half-phase offset between left and right legs would significantly improve realism.

**Estimated work**: 0.5 hours — add left/right phase offset, include head in body chain, increase undulation amplitude

---

### B-5: Guster — Overall: 5/10 | Potential: 6/10

**Model**: 9 bones: root, tornado, tornado2, tornadomid, tornado3, tornado4, eyes, eye_left, eye_right

**What's good**:
- Unique tornado rotation: each segment has cumulative rotation for spiraling effect
- Circular orbital motion: `tornado.rotationPointX += cos(ageInTicks * 0.7F) * 4F` — tornado segments orbit
- Eye tracking with googly-eyes mode (faster eye movement when googly)
- `chainFlap` on tornado segments for vertical pulsing
- Wind elemental pulsing via `setScale()` on tornado segments
- Separate `animateGust()` for smaller gust variant

**What's weak**:
- The rotationPointX/Z orbital motion has a bug: after setting orbital positions, it does `tornado.rotationPointX += Math.cos(...) * 2F - tornado.rotationPointX` which immediately subtracts the position just set — this creates a jittery position rather than a smooth orbit
- The cumulative rotation: `tornado2.rotateAngleY -= tornado.rotateAngleY + ageInTicks * 0.3F` — subtracting the parent's rotation creates a counter-rotation effect that's hard to follow visually
- The tornado is essentially invisible (wind elemental) — the animation quality matters less because players see particles, not the model
- Eyes are the only visible part — they bob and track but could have more character
- Breathing scale changes are very small: `tornado.setScale(1.0F + windPulse, ...)` where `windPulse = cos(...) * 0.03F` — only 3% scale change

**What's missing completely**:
- **Wind intensity variation**: The tornado should have varying spin speed based on movement
- **Eye squint/blink**: Eyes are the only visible feature — they should blink or squint

**Why B-tier**: Unique tornado mechanics but the orbital bug creates jitter. As an invisible creature, perfection matters less. Eyes could have more character.

**Estimated work**: 0.5 hours — fix orbital bug, add eye animation variety, increase pulse scale visibility

---

### B-6: Farseer — Overall: 6/10 | Potential: 7/10

**Model**: 36 bones: root, 2 body cubes, head with 4 mask parts, eye, 4 arms with elbow+hand+fingers (16 finger bones) — COMPLEX MODEL

**What's good**:
- The latency system (`getLatencyVar()`, `getLatencyOffsetVec()`) is the most sophisticated animation data pipeline in the mod — each arm and body part follows a delayed position from the entity's movement history
- Arm float idle: each arm drifts independently with unique phase offsets (1.3F, 1.6F, 1.9F, 2.3F...)
- Angry state: mask parts flare open, body cubes shift, head shakes with angry vector
- Strike/Clasp progress for each of 4 arms with finger articulation
- EMERGE keyframe animation with cinematic arm movement
- Breathing via head scale + body cube position
- Head tracking with camera-facing awareness (`invPD` = inverse of facing-camera amount)

**What's weak**:
- The complexity makes the code nearly unreadable — 16 finger bones, 4 arms, all with individual progress rotations
- The `animate()` method has 4 keyframes of EMERGE that are mostly copy-paste with slight position variations
- Eye (the creature's defining feature) has no pupil dilation or independent movement — it's a static cube attached to head
- Mask parts (upper/lower left/right) have symmetrical animation — they should have slight asymmetry for organic feel
- Breathing is in `head.setScale()` but barely visible at 1.5% scale change

**What's missing completely**:
- **Pupil dilation**: The eye is the creature's identity — it should dilate based on alertness
- **Eyelid blink**: A slow, deliberate blink that covers the eye
- **Independent eye rotation**: The eye should be able to look around independently from head

**Why B-tier**: Incredibly sophisticated latency system and finger articulation. The eye itself is the weak point — static, no dilation, no blink.

**Estimated work**: 0.75 hours — add pupil dilation, add slow eyelid blink, add eye rotation independence

---

### B-7: Mungus — Overall: 5/10 | Potential: 6/10

**Model**: 8 bones: root, body, hair, eye, leg_left, leg_right, nose, sack

**What's good**:
- Soft-body squash: `body.setScale(body.getScaleX(), 1.0F - squash * 0.06F, body.getScaleZ())` — body compresses on step impact, unique in the mod
- Glow sack with dynamic swell: `sack.setScale(glowyBob, glowyBob, glowyBob + swell * 0.2F)` — sack grows with swell progress
- Eye tracking: eye follows camera position with Y and X offset
- Nose wiggle: independent side-to-side micro-movement
- Walking uses walk+bob on legs with body flap — soft creature gait
- Breathing visible via body/sack position offsets
- Hair sway at idle

**What's weak**:
- Only 2 legs — the walk is a bipedal shuffle with no clear gait architecture
- The squash effect only happens on the Y-axis (vertical) — real soft bodies compress in all directions, expanding on the axes perpendicular to compression (conservation of volume)
- Sack swell is linear with swell progress — no pulsing or rhythmic expansion
- The eye tracking is direct position manipulation, not using `faceTarget()` — works but could conflict
- Hair is a single flat bone — can't articulate individual strands
- Nose wiggle is very small: `Mth.sin(ageInTicks * 0.3F) * 0.03F`

**What's missing completely**:
- **Volume conservation**: When the body squashes vertically, it should expand horizontally (wider when squished)
- **Spore puff from sack**: The sack should periodically puff spores (scale pulse + position offset)
- **Leg differentiation**: Two legs, but both are identical in animation

**Why B-tier**: The soft-body squash is unique and well-implemented. Adding volume conservation and spore puff would maximize the model.

**Estimated work**: 0.5 hours — add volume conservation on squash, add spore puff, differentiate legs slightly

---

### B-8: Straddler — Overall: 3/10 | Potential: 5/10

**Model**: 9 bones: root, body, hair, horn_left, hair_left, horn_right, hair_right, leg_left, leg_right

**What's good**:
- ModelAnimator LAUNCH sequence with horn/hair repositioning
- Hair idle sway (walk + swing + flap)
- Walk uses walk on legs and swing/flap/walk on body — multi-axis locomotion
- Breathing via body rotationPointY
- Hair flap at idle for wind effect

**What's weak**:
- Only 9 bones — limited expressive capacity
- "Board-rider" identity is told not shown — there's no board balance mechanic, no leaning into turns, no wave-riding posture
- Walk is a generic bipedal shuffle with identical left/right leg parameters: `walk(leg_right, walkSpeed, walkDegree * 1.5F, false, 0, 0F, ...)` and `walk(leg_left, walkSpeed, walkDegree * 1.5F, true, 0, 0F, ...)` — only invert differs
- Horns are static at idle — only move during LAUNCH animation
- No arm bones — can't express "arms out for balance" surfing posture
- The hair on horns (hair_left, hair_right) gets identical swing parameters

**What's missing completely**:
- **Board balance sway**: Body should rock side-to-side and front-to-back as if balancing on a moving board
- **Wave-riding lean**: Body should lean into turns
- **Arm-equivalent balance**: Without arm bones, the horns could serve as balance counterweights

**Ceiling explanation**: 9 bones with no arms. The "surfer" identity requires arms for balance — model simply doesn't have them. Ceiling of 5/10 is limited by the model structure.

**Why B-tier**: The surfer identity is entirely absent from procedural animation. Adding board balance sway and turn-leaning would maximize what the model allows.

**Estimated work**: 0.5 hours — add board balance sway, add turn lean, use horns as counterweights

---

### B-9: Stradpole — Overall: 3/10 | Potential: 5/10

**Model**: 5 bones: root, body, hair_left, hair_right, tail

**What's good**:
- Body + tail swim undulation: `swing(body, ...)` + `swing(tail, walkSpeed * 1.4F, walkDegree * 2F, ...)` — tail has higher amplitude than body, correct for tadpole locomotion
- Hair gill flutter: left/right hair flap independently
- Swim pitch tracking from entity data
- Breathing via body rotationPointY + hair micro-flaps
- Face target for head orientation

**What's weak**:
- Only 5 bones — extremely limited model
- The tail is a single flat bone — can't express S-curve undulation (requires at least 2-3 tail segments)
- Body swing uses `swing(body, walkSpeed, walkDegree * 0.4F, true, 2, 0F, ...)` + `flap(body, walkSpeed, walkDegree * 0.2F, true, 0, 0F, ...)` — the body undulation is minimal
- Tail amplitude: `walkDegree * 2F` = only 0.8 radians — should be larger for dramatic tadpole wiggling
- Hair gills are flat bones that only flap — can't ripple along their length
- No mouth or eye bones — can't express feeding or tracking behavior

**What's missing completely**:
- **S-curve tail undulation**: Requires multiple tail segments (model limitation)
- **Feeding filter motion**: Tadpoles filter-feed by creating a water current with their mouth
- **Metamorphosis transition**: Juvenile-to-adult transformation (not in model scope)

**Ceiling explanation**: 5 bones is the lowest in the mod. The tail is a single bone — can't do S-curves. 5/10 is the absolute ceiling for a 5-bone creature.

**Why B-tier**: The model severely limits what's possible. Current animation uses what's available correctly. Increasing tail amplitude and adding body-tail phase offset would maximize the model.

**Estimated work**: 0.25 hours — increase tail amplitude, add body-tail phase offset, differentiate hair gill frequencies

---

### B-10: Potoo — Overall: 5/10 | Potential: 6/10

**Model**: 12 bones: root, body, tail, left_wing, right_wing, left_eye/pupil, right_eye/pupil, head, left_foot, right_foot

**What's good**:
- Eye pupil scale for giant eye bulge: `left_pupil.setScale(0.5F, 1.0F+eyeScale*2.1F, 1.0F+eyeScale*2.1F)` — eyes dominate the face, correct for potoo
- Frog-mouth gape: `head.setScale(1.0F+mouthProgress*0.3F, 1.0F, 1.0F)` — head stretches for the huge yawn
- Sleep state: eyes hidden when sleeping (potoos sleep with eyes closed to slits)
- Statue-still perch: barely any movement when perched (correct for camouflage)
- Flight flap with bob
- Ground walk with foot walk

**What's weak**:
- The "statue-still camouflage" identity is mostly in the LACK of animation — which is correct, but makes the creature feel under-animated
- Breathing is nearly invisible: `breath * 0.03F` on rotationPointY — potoos should have barely visible breathing while camouflaged, but slightly more visible when active
- The frog-mouth gape only scales the head on X-axis — the lower beak should drop open for the full frog-mouth effect
- Ground walk: only 2 bones (`left_foot`, `right_foot`) are animated with a simple walk loop
- No neck articulation — the head rotates for tracking but has no neck bone (model limitation)
- Body is a single block — the "broken branch" posture is achieved through rotation, not body deforming

**What's missing completely**:
- **Vertical branch-perch posture**: Potoos perch VERTICALLY on branches to look like broken stumps — this is their signature camouflage posture
- **Slow head rotation**: Potoos rotate their head very slowly when tracking prey — the current `head.rotateAngleY += netHeadYaw * 0.5F` is too responsive
- **Wing droop when perched**: Wings should slightly droop to complete the "broken branch" silhouette

**Why B-tier**: The eye bulge and frog-mouth gape are excellent. The vertical perch posture and slow head rotation would complete the camouflage identity.

**Estimated work**: 0.5 hours — add vertical perch posture option, slow head rotation, add wing droop during perch

---

### B-11: AlligatorSnappingTurtle — Overall: 5/10 | Potential: 7/10

**Model**: 14 bones: root, body, arm L/R, leg L/R, shell, spikes L/R, neck, head, head_inside, jaw, tail

**What's good**:
- Jaw snap mechanics: open mouth → anticipatory head retraction → explosive forward strike with neck scale stretch
- Diagonal gait for walking: legs and arms have correct phase offsets (`true 1.0F` / `false 1.0F` / `false 0.0F` / `true 0.0F`)
- Body bob for heavy weight transfer during walk
- Tail counter-steering during locomotion
- Neck and head follow-through during walk
- Head stabilization with reduced tracking sensitivity (`netHeadYaw * 0.4F`)
- Jaw open progress with neck rotation and head angling
- Breathing via body rotationPointY + neck angle

**What's weak**:
- Breathing is invisible (`rotationPointY` only, no `setScale`) — a creature this large should have visible chest/shell expansion
- The snap uses progressive position/rotation — there's no "explosive" speed differentiation. The snap should be SLOW windup → INSTANT strike
- Walk degree differentiation between water and land (`0.9F` vs `0.65F`) is good, but the walk uses only `swing()` on limbs — no knee articulation
- The tail swing uses `walkSpeed * 1.35F` — faster than the walk itself, creating a tail-wag that's too energetic for a heavy turtle
- Shell (`shell`) bone exists but has NO independent animation — it should rock slightly independently from the body
- Spikes (`spikes_left`, `spikes_right`) are completely static — they should have subtle vibration or threat-erection

**What's missing completely**:
- **Lure-tongue wiggle**: The tongue-lure (worm-shaped) that attracts fish — this is THE signature alligator snapping turtle behavior and is not expressed in the model at all (the tongue may not be a separate bone)
- **Explosive snap speed curve**: Slow wind-up → pause → instant strike (currently smooth progress)
- **Visible shell expansion for breathing**: Shell should slightly expand/contract

**Why B-tier**: Good foundation with proper gait and snap mechanics. Missing the lure-tongue wiggle (signature behavior). Breathing is invisible. Snap has no speed curve.

**Estimated work**: 0.75 hours — add explosive snap speed curve (2-stage), add visible breathing via shell scale, investigate lure-tongue possibility, reduce tail wag energy

---

### B-12: FrilledShark — Overall: 5/10 | Potential: 6/10

**Model**: 10 bones: root, body, head, jaw, pectoralfin L/R, tail1, tail2, pelvicfin L/R

**What's good**:
- Eel-like swimming via `chainSwing` on head→body→tail1→tail2 — correct for anguilliform locomotion
- Land state: body rotates 90 deg for beached posture
- Jaw idle micro-movement: `walk(jaw, idSp, idDg, true, 1F, -0.1F, ageInTicks, 1)` — subtle jaw clench
- Breathing visible via `body.setScale()`
- Pectoral fin flap with left/right asymmetry: right uses `idDg*2F`, left uses `idDg*-2F` in land state
- Pelvic fin flap during swim
- Body bob during swim for vertical undulation component

**What's weak**:
- `chainSwing` swimming is correct but the amplitude is uniform across all segments: `chainSwing(tailBoxes, swSp, swDg*0.9F, -3, ...)` — real anguilliform swimmers have INCREASING amplitude toward the tail (head moves least, tail moves most). `chainWave` would be better for amplitude gradient
- The jaw idle walk is very subtle: `walk(jaw, 0.14F, 0.25F, true, 1F, -0.1F, ...)` — frilled sharks have distinctive needle-like teeth and a wide gape
- Pectoral fins during swim: `flap(pectoralfin_right, swSp, swDg, true, 1F, 0.3F, ...)` — identical parameters for both fins
- Only 10 bones limits expressiveness
- The frilled gills (the creature's namesake) are not separate bones — the "gill ripple" is simulated by body scale breathing, which is a reasonable workaround

**What's missing completely**:
- **Amplitude gradient in anguilliform swimming**: head < body < tail1 < tail2 (requires `chainWave` or manual amplitude scaling)
- **Gill frill ripple**: The frilled gills that give the shark its name — could be simulated by rapid scale pulsing on the body
- **Primitive jaw articulation**: Frilled sharks have a unique jaw structure with widely spaced needle teeth

**Why B-tier**: Correct swimming approach but uniform amplitude. Swapping `chainSwing` for `chainWave` (which has built-in amplitude gradient) would significantly improve realism.

**Estimated work**: 0.5 hours — swap chainSwing for chainWave swimming, differentiate pectoral fin motion, add gill ripple via rapid scale pulse

---

### B-13: GiantSquid — Overall: 7/10 | Potential: 8/10

**Model**: 39 bones — THE LARGEST MODEL in the mod: head, beak, 8 short tentacles (each 2 segments), 2 long feeding arms (each 5 segments), mantle, mantle_end, 2 membranes, eyes with pupils

**What's good**:
- **Jet propulsion**: Asymmetric mantle contraction cycle — RAPID contraction (expels water) → SLOW expansion (refills). Modeled with `abs(sin)` for contraction + slow recovery bias. This is biomechanically correct — squid jet propulsion is asymmetric
- **Fin undulation**: Left/right membrane undulation with traveling wave + breathing sync
- **Tentacle curl**: All 8 short tentacles have UNIQUE `chainSwing` frequencies (0.08, 0.09, 0.10, 0.11F) and amplitudes (0.25, 0.28, 0.2, 0.18F) — excellent independence
- **Feeding arms**: 3D spiral curl via `chainSwing` + `chainFlap` on each 5-segment arm — creates true 3D arm motion
- **Breathing**: mantle scale, mantle_end scale, head position, beak angle — multi-part breathing
- **State transitions**: dry, captured, grab states with full arm/tentacle repositioning
- **Eye tracking**: advanced pupil position with distance-based scale
- **Beak micro-clatter**: beak vibrates during grab state

**What's weak**:
- The jet propulsion is excellent but the `limbSwingAmount` multiplier means jet only activates when the squid is moving — stationary squids should still breathe/pulse
- Tentacle chainSwing uses `1-dryProgress*0.5F` to reduce amplitude when dry — works but amplitude could scale more gradually
- The membrane (fin) undulation is subtle — could be slightly more visible
- Grab state arm contraction uses complex `getArmRot()` latency — hard to visually verify but code is sophisticated

**What's missing completely**:
- Very little. This is already at near-ceiling quality.
- **Ink cloud ejection posture**: Squid contorts mantle to eject ink — not currently expressed
- **Arm-tip club expansion**: Giant squid arms have expanded clubs at tips — `left_hand`/`right_hand` could pulse

**Why B-tier (near A)**: This creature is very close to its ceiling. Minor polish on jet propulsion at idle and slightly more visible fin undulation would push it to 8/10.

**Estimated work**: 0.25 hours — ensure jet breathing at idle, slightly increase fin undulation visibility

---

### B-14: HammerheadShark — Overall: 7/10 | Potential: 7/10

**Model**: 15 bones: root, main_body, head, head_hammer, topfin, topfintail, tail1, tail2, tail3, tailbottomend, tailtopend, finL, finR, tail_finL, tail_finR

**What's good**:
- **Thunniform swimming**: `chainWave` on tail1→tail2→tail3 — only the tail undulates, body stays rigid. This is biomechanically CORRECT for sharks (unlike eels)
- **Hammer head electroreceptive scanning**: Dual-frequency head sweep — slow wide scan (0.12F) + fast micro-scan (0.4F) — simulates ampullae of Lorenzini sweeping like a metal detector
- **Pectoral fins as stabilizers** (not rowers): fins `flap()` gently + have passive current sway — correct shark biomechanics
- **Dorsal fins**: iconic silhouette with passive sway + swim-driven pitch
- **Heterocercal tail**: top lobe longer than bottom — `tailtopend` and `tailbottomend` have different amplitudes
- **Breathing**: visible via `main_body.setScale()` — ram ventilation expansion
- **Body lateral recoil**: `main_body.rotationPointX += sin(limbSwing * swimSpeed + 1.5F) * ...` — body recoils opposite to tail thrust, correct physics

**What's weak**:
- The `chainWave` amplitude is uniform across tail segments — `chainWave` with phase offset `0.2F` creates a traveling wave but all 3 segments have the same amplitude. Real thunniform swimmers have INCREASING amplitude: tail1 < tail2 < tail3
- The head scanning at idle uses `(1-limbSwingAmount*0.5F)` and `(1-limbSwingAmount*0.6F)` to reduce scanning when moving — correct, but the idle scan could be more pronounced when the shark is stationary
- Pectoral fin stabilizer motion is subtle — could have slightly more pronounced banking during turns

**What's missing completely**:
- Very little. The creature is at its ceiling.
- **Ampullary sensing "found prey" head twitch**: When the shark detects prey, the head should twitch toward it
- **Breach behavior**: Hammerheads occasionally breach the surface (not in current model scope)

**Why B-tier (near ceiling)**: Excellent thunniform biomechanics and hammer-head scanning. Adding amplitude gradient to `chainWave` tail would be the final polish.

**Ceiling explanation**: 7/10 is the ceiling for this model. The 15 bones are well-used. Further improvement would require more tail vertebrae bones or a dynamic jaw.

**Estimated work**: 0.25 hours — add amplitude gradient to chainWave (or manually scale tail2/tail3 amplitudes), add prey-detection head twitch

---

## PRIORITY C — AT CEILING (49 creatures)

These creatures have already been redesigned in prior sessions and are at or near their model ceilings. No further work needed. See ANIMATION_PROGRESS.md for the complete identity catalog.

---

## WORK ESTIMATE

| Priority | Creatures | Estimated Hours | Difficulty |
|---|---|---|---|
| A | 15 | 18 hours | High — major feature additions |
| B | 15 | 8 hours | Medium — targeted polish |
| C | 49 | 0 hours | Done — at ceiling |
| **Total** | **79 remaining** | **26 hours** | |

---

## RECOMMENDED EXECUTION ORDER

### Phase 1 — Quick Wins (Priority B):
1. B-14: HammerheadShark (0.25h)
2. B-13: GiantSquid (0.25h)
3. B-12: FrilledShark (0.5h)
4. B-11: AlligatorSnappingTurtle (0.75h)
5. B-10: Potoo (0.5h)
6. B-9: Stradpole (0.25h)
7. B-8: Straddler (0.5h)
8. B-7: Mungus (0.5h)
9. B-6: Farseer (0.75h)
10. B-5: Guster (0.5h)
11. B-4: Endergrade (0.5h)
12. B-3: Flutter (0.5h)
13. B-2: WarpedMosco (0.5h)
14. B-1: MimicOctopus (0.75h)

### Phase 2 — Major Improvements (Priority A):
1. A-11: Fly (0.5h) — model-limited, quick
2. A-13: Skelewag (0.75h)
3. A-5: RockyRoller (1.0h)
4. A-14: Lobster (1.0h)
5. A-4: RainFrog (1.0h)
6. A-9: LeafcutterAnt (1.0h)
7. A-2: Terrapin (1.0h)
8. A-12: Platypus (1.0h)
9. A-1: Cockroach (1.5h)
10. A-6: Bunfungus (1.5h)
11. A-8: FlyingFish (1.5h)
12. A-10: CrimsonMosquito (1.5h)
13. A-7: Mudskipper (1.5h)
14. A-3: SugarGlider (1.5h)
15. A-15: MantisShrimp (1.5h)

---

## DESIGN PHILOSOPHY

> **Identity from natural behavior, not showmanship.**
> Every creature must be instantly recognizable by its movement alone, even without textures.
> No two creatures share animation formulas, motion curves, amplitudes, frequencies, offsets, or timing.
> Push every creature to its MODEL CEILING — the hard limit imposed by bone count and hierarchy.
