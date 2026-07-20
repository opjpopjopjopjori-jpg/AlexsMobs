# Lessons Learned

## 1. Static Analysis ≠ Complete Testing

### ❌ What We Thought
- Static analysis finds all bugs
- If code compiles, it works
- Automated checks are enough

### ✅ What We Learned
- Static analysis finds **syntax** errors
- Visual/animation bugs need **in-game** testing
- Axis errors only visible when entity moves
- Progress values need gameplay validation

### 💡 Key Insight
**Static analysis is necessary but not sufficient. Always test in-game.**

---

## 2. Network Restrictions Are Real

### ❌ What We Tried
- Download JDK from 15+ sources
- Use apt, pip, npm
- SSH tunnels, proxies
- GitHub Actions

### ✅ What We Learned
- E2B proxy only allows 3 domains:
  - `api.github.com`
  - `pypi.org` / `files.pythonhosted.org`
  - `registry.npmjs.org`
- All other HTTPS connections blocked at TLS level
- No workaround within sandbox

### 💡 Key Insight
**Environment constraints are hard limits. Plan accordingly. Use local builds when network is restricted.**

---

## 3. Forge vs NeoForge Confusion

### ❌ What We Thought
- `net.neoforged:forge` = NeoForge (separate project)
- Mod won't work on Forge

### ✅ What We Learned
- In 1.20.1: `net.neoforged:forge` = Forge (namespace change only)
- NeoForge split happened **after** 1.20.2
- AlexsMobs 1.20.1 works on Forge ✅

### 💡 Key Insight
**Check version history. Namespace changes don't always mean different projects.**

---

## 4. Keyframe Balance is Critical

### ❌ What We Found
- `ModelGrizzlyBear.java` had 2 stray `endKeyframe()` calls
- After `resetKeyframe()`, not after `startKeyframe()`
- Corrupts keyframe stack → IllegalStateException

### ✅ What We Learned
- `resetKeyframe()` already closes keyframe
- Adding `endKeyframe()` after it = corruption
- Always verify: starts == ends

### 💡 Key Insight
**Keyframe stack must be balanced. Every `startKeyframe()` needs exactly one `endKeyframe()`. `resetKeyframe()` does NOT need `endKeyframe()`.**

---

## 5. Axis Mapping Changes with Rotation

### ❌ What We Thought
- Y axis = yaw (always)
- Z axis = roll (always)

### ✅ What We Learned
- When body rotates 90° (standing), axis mapping changes
- In standing state: Z axis becomes yaw in world space
- Original code was correct for standing state

### 💡 Key Insight
**Axis mapping depends on parent rotation. Verify coordinate system in each state.**

---

## 6. Progress Variables Need Interpolation

### ❌ What We Found
- Some models used `entity.sitProgress` directly
- No interpolation with `partialTick`
- Jerky transitions

### ✅ What We Learned
- Always interpolate: `prev + (current - prev) * partialTick`
- Smooth transitions require frame timing
- Entity fields update per tick, not per frame

### 💡 Key Insight
**Always interpolate progress variables. Use prevX and X fields with partialTick.**

---

## 7. Scale Must Be Reset

### ❌ What We Found
- 20+ files didn't reset scale in `renderToBuffer()`
- Baby head scale persisted to adults
- Visual glitch

### ✅ What We Learned
- Scale is persistent (not reset automatically)
- Always reset after young branch
- Reset ALL scaled bones

### 💡 Key Insight
**Scale is stateful. Always reset in render method.**

---

## 8. Duplicate Calls Waste Performance

### ❌ What We Found
- 7 files had redundant `resetToDefaultPose()` calls
- Called in both `setupAnim()` and `animate()`
- Double processing

### ✅ What We Learned
- `resetToDefaultPose()` resets all bones
- Calling twice = 2x work
- Only call once (in `animate()`)

### 💡 Key Insight
**Avoid redundant operations. Profile to find performance bottlenecks.**

---

## 9. AI Has Limitations

### ❌ What We Expected
- AI can test animations visually
- AI can run builds
- AI can verify in-game behavior

### ✅ What We Learned
- AI can only do static analysis
- No GUI/display = no visual testing
- No Java/Gradle = no builds
- Network restrictions = no downloads

### 💡 Key Insight
**AI is a tool, not a replacement. Human testing is essential for visual/behavioral validation.**

---

## 10. Documentation Prevents Repeated Mistakes

### ❌ What Happened
- Fixed same bug types multiple times
- Forgot patterns between sessions
- Re-discovered issues

### ✅ What We Learned
- Document bugs and fixes
- Create checklists
- Build automated validation
- Share knowledge

### 💡 Key Insight
**Document everything. Future you (and others) will thank you.**

---

## 11. Testing Scripts Save Time

### ❌ Manual Testing
- Summon each entity manually
- Test each state manually
- Time-consuming and error-prone

### ✅ Automated Testing
- Python + mcrcon script
- Stress test all entities
- Reproducible and fast

### 💡 Key Insight
**Automate repetitive tasks. Scripts are faster and more reliable than manual testing.**

---

## 12. Small Fixes Can Have Big Impact

### ❌ What We Thought
- Changing one axis won't matter much
- Small performance improvements aren't worth it

### ✅ What We Learned
- Wrong axis = completely broken animation
- 2x performance improvement is noticeable
- Small fixes compound

### 💡 Key Insight
**Don't underestimate small fixes. They often have outsized impact.**

---

## 13. Context Matters

### ❌ What We Assumed
- Same fix applies everywhere
- One pattern fits all

### ✅ What We Learned
- Standing state changes axis mapping
- Different entities have different needs
- Context determines correct solution

### 💡 Key Insight
**Understand context before applying fixes. One size doesn't fit all.**

---

## 14. Collaboration Amplifies Results

### ❌ Solo Work
- Limited perspective
- Blind spots
- Slower progress

### ✅ Collaborative Work
- Multiple perspectives
- Catch more bugs
- Faster iteration

### 💡 Key Insight
**Collaborate. Two heads are better than one.**

---

## 15. Ship Incrementally

### ❌ What We Tried
- Fix everything at once
- Perfect before shipping

### ✅ What We Learned
- Ship working code
- Iterate based on feedback
- Perfect is enemy of good

### 💡 Key Insight
**Ship early, iterate often. Don't wait for perfection.**
