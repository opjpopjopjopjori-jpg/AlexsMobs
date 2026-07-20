package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntityCatfish;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.util.Mth;

public class ModelCatfishLarge extends AdvancedEntityModel<EntityCatfish> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox body;
    private final AdvancedModelBox left_fin;
    private final AdvancedModelBox right_fin;
    private final AdvancedModelBox dorsal_fin;
    private final AdvancedModelBox tail;
    private final AdvancedModelBox tail_fin;
    private final AdvancedModelBox head;
    private final AdvancedModelBox left_BigWhisker;
    private final AdvancedModelBox right_BigWhisker;
    private final AdvancedModelBox left_SmallWhisker;
    private final AdvancedModelBox right_SmallWhisker;

    public ModelCatfishLarge() {
        texWidth = 128;
        texHeight = 128;

        root = new AdvancedModelBox(this, "root");
        root.setRotationPoint(0.0F, 24.0F, 0.0F);


        body = new AdvancedModelBox(this, "body");
        body.setRotationPoint(0.0F, -8.0F, 0.0F);
        root.addChild(body);
        body.setTextureOffset(0, 0).addBox(-10.0F, -7.0F, -14.0F, 20.0F, 15.0F, 26.0F, 0.0F, false);

        left_fin = new AdvancedModelBox(this, "left_fin");
        left_fin.setRotationPoint(10.0F, 5.0F, -5.0F);
        body.addChild(left_fin);
        setRotationAngle(left_fin, 0.0F, 0.0F, 0.6981F);
        left_fin.setTextureOffset(0, 0).addBox(0.0F, 0.0F, -2.0F, 5.0F, 0.0F, 8.0F, 0.0F, false);

        right_fin = new AdvancedModelBox(this, "right_fin");
        right_fin.setRotationPoint(-10.0F, 5.0F, -5.0F);
        body.addChild(right_fin);
        setRotationAngle(right_fin, 0.0F, 0.0F, -0.6981F);
        right_fin.setTextureOffset(0, 0).addBox(-5.0F, 0.0F, -2.0F, 5.0F, 0.0F, 8.0F, 0.0F, true);

        dorsal_fin = new AdvancedModelBox(this, "dorsal_fin");
        dorsal_fin.setRotationPoint(0.0F, -7.0F, -5.0F);
        body.addChild(dorsal_fin);
        setRotationAngle(dorsal_fin, -0.0873F, 0.0F, 0.0F);
        dorsal_fin.setTextureOffset(0, 0).addBox(0.0F, -7.0F, 0.0F, 0.0F, 7.0F, 8.0F, 0.0F, false);

        tail = new AdvancedModelBox(this, "tail");
        tail.setRotationPoint(0.0F, -2.0F, 13.0F);
        body.addChild(tail);
        tail.setTextureOffset(0, 41).addBox(-6.0F, -5.0F, -1.0F, 12.0F, 11.0F, 20.0F, 0.0F, false);
        tail.setTextureOffset(0, 56).addBox(0.0F, 6.0F, -1.0F, 0.0F, 4.0F, 16.0F, 0.0F, false);
        tail.setTextureOffset(16, 18).addBox(0.0F, -7.0F, 4.0F, 0.0F, 2.0F, 5.0F, 0.0F, false);

        tail_fin = new AdvancedModelBox(this, "tail_fin");
        tail_fin.setRotationPoint(0.0F, 0.0F, 17.0F);
        tail.addChild(tail_fin);
        tail_fin.setTextureOffset(44, 18).addBox(0.0F, -10.0F, -5.0F, 0.0F, 17.0F, 23.0F, 0.0F, false);

        head = new AdvancedModelBox(this, "head");
        head.setRotationPoint(0.0F, -5.0F, -15.0F);
        body.addChild(head);
        head.setTextureOffset(56, 64).addBox(-9.5F, -2.0F, -7.0F, 19.0F, 13.0F, 8.0F, 0.0F, false);

        left_BigWhisker = new AdvancedModelBox(this, "left_BigWhisker");
        left_BigWhisker.setRotationPoint(9.5F, 6.0F, -5.0F);
        head.addChild(left_BigWhisker);
        setRotationAngle(left_BigWhisker, 0.0F, -0.4363F, 0.2618F);
        left_BigWhisker.setTextureOffset(66, 0).addBox(0.0F, 0.0F, 0.0F, 15.0F, 8.0F, 0.0F, 0.0F, false);

        right_BigWhisker = new AdvancedModelBox(this, "right_BigWhisker");
        right_BigWhisker.setRotationPoint(-9.5F, 6.0F, -5.0F);
        head.addChild(right_BigWhisker);
        setRotationAngle(right_BigWhisker, 0.0F, 0.4363F, -0.2618F);
        right_BigWhisker.setTextureOffset(66, 0).addBox(-15.0F, 0.0F, 0.0F, 15.0F, 8.0F, 0.0F, 0.0F, true);

        left_SmallWhisker = new AdvancedModelBox(this, "left_SmallWhisker");
        left_SmallWhisker.setRotationPoint(9.5F, 8.0F, -6.0F);
        head.addChild(left_SmallWhisker);
        setRotationAngle(left_SmallWhisker, 0.0F, 0.0436F, 0.3054F);
        left_SmallWhisker.setTextureOffset(0, 15).addBox(0.0F, 0.0F, 0.0F, 7.0F, 6.0F, 0.0F, 0.0F, false);

        right_SmallWhisker = new AdvancedModelBox(this, "right_SmallWhisker");
        right_SmallWhisker.setRotationPoint(-9.5F, 8.0F, -6.0F);
        head.addChild(right_SmallWhisker);
        setRotationAngle(right_SmallWhisker, 0.0F, -0.0436F, -0.3054F);
        right_SmallWhisker.setTextureOffset(0, 15).addBox(-7.0F, 0.0F, 0.0F, 7.0F, 6.0F, 0.0F, 0.0F, true);
        this.updateDefaultPose();
    }

    @Override
    public void setupAnim(EntityCatfish entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        //══════ 🐟 CATFISH — WHISKERED BOTTOM-FEEDER ══════
        // IDENTITY: Barbels (whiskers) drag and sense bottom. Subcarangiform
        // swimming wave. Dorsal fin ripples. Slow deliberate bottom-dweller.
        // UNIQUE vs CosmicCod (drifter), FlyingFish (glider), Pupfish (darter).

        // ── Core State ──────────────────────────────────────────────
        boolean inWater = entity.isInWater();
        float partialTick = ageInTicks - entity.tickCount;
        float spitProgress = entity.getSpitTime() > 0 ? (entity.getSpitTime() - partialTick) / 10.0F : 0;
        float swallowedProgress = entity.hasSwallowedEntity() ? 1.0F : 0.0F;

        // ── Animation Parameters ────────────────────────────────────
        // Swimming: subcarangiform wave — amplitude grows from head→tail
        float swimSpeed = 0.55F;
        float swimDegree = 0.75F;

        // ── AAA BREATHING: Operculum Pumping ────────────────────────
        // Catfish constantly pump water over gills — subtle rhythmic body expansion
        float breathCycle = Mth.cos(ageInTicks * 0.12F);
        float breathIntensity = 0.02F;
        // Body subtly expands/contracts to simulate gill pumping
        body.setScale(1.0F + breathCycle * breathIntensity,
                      1.0F + breathCycle * breathIntensity * 0.7F,
                      1.0F);
        // Head position shifts with each "pump"
        head.rotationPointY += breathCycle * 0.12F;
        // Subtle mouth opening during breathing (ventral mouth drops slightly)
        head.rotateAngleX += breathCycle * 0.015F;

        // ── AAA SWIMMING: True Subcarangiform Body Wave ──────────────
        // Real fish: traveling wave starts ~30% body length from head,
        // amplitude increases exponentially toward tail
        // Phase offset between body→tail→tail_fin creates the traveling illusion
        if (inWater) {
            // Body: subtle lateral oscillation (wave origin)
            this.swing(body, swimSpeed, swimDegree * 0.15F, false, 1.0F, 0F, limbSwing, limbSwingAmount);
            // Tail: stronger swing — phase lags behind body
            this.swing(tail, swimSpeed, swimDegree * 0.65F, false, 0.3F, 0F, limbSwing, limbSwingAmount);
            // Tail fin: maximum amplitude — whip-like terminus of the wave
            this.swing(tail_fin, swimSpeed, swimDegree * 1.2F, false, -1.0F, 0F, limbSwing, limbSwingAmount);
            // Head counter-swing: stabilizes the front, prevents wobble
            this.swing(head, swimSpeed, swimDegree * 0.08F, true, 2.0F, 0F, limbSwing, limbSwingAmount);
            // Body vertical undulation for 3D water column navigation
            float bodyBob = Mth.sin(limbSwing * swimSpeed * 0.8F + 1.5F) * swimDegree * 0.25F * limbSwingAmount;
            body.rotationPointY += bodyBob;
            head.rotationPointY -= bodyBob * 0.3F;
            tail.rotationPointY -= bodyBob * 0.5F;
            tail_fin.rotationPointY -= bodyBob * 0.7F;
        }

        // ── AAA PECTORAL FIN MECHANICS ──────────────────────────────
        // Fins perform figure-8 rowing: combine flap (vertical) + swing (horizontal)
        // Creates the characteristic oar-like propulsion of catfish
        if (inWater) {
            // Primary rowing motion — figure-8 through combined axes
            this.flap(left_fin, swimSpeed, swimDegree * 0.5F, false, 3.0F, -0.4F, limbSwing, limbSwingAmount);
            this.flap(right_fin, swimSpeed, swimDegree * 0.5F, true, 3.0F, -0.4F, limbSwing, limbSwingAmount);
            // Subtle fore-aft rowing component
            this.walk(left_fin, swimSpeed, swimDegree * 0.3F, false, 2.0F, 0.2F, limbSwing, limbSwingAmount);
            this.walk(right_fin, swimSpeed, swimDegree * 0.3F, true, 2.0F, 0.2F, limbSwing, limbSwingAmount);
        }

        // ── AAA DORSAL FIN ADJUSTMENT ───────────────────────────────
        // Dorsal fin subtly adjusts angle based on swimming intensity
        // Erects slightly during active swimming for stability
        if (inWater) {
            float dorsalAdjust = Mth.abs(Mth.sin(limbSwing * swimSpeed)) * limbSwingAmount * 0.08F;
            dorsal_fin.rotateAngleX += dorsalAdjust;
        }

        // ── AAA IDLE BEHAVIOR ───────────────────────────────────────
        float idleSpeed = 0.18F;
        float idleDegree = 0.2F;
        float idleAmount = 1.0F - (inWater ? limbSwingAmount * 0.5F : 0F);

        // Subtle buoyancy drift — catfish hovering in water column
        float hoverDrift = Mth.sin(ageInTicks * 0.07F + 1.3F) * 0.4F;
        float hoverDrift2 = Mth.cos(ageInTicks * 0.09F + 0.5F) * 0.25F;
        body.rotationPointY += hoverDrift * idleAmount;
        body.rotationPointZ += hoverDrift2 * idleAmount * 0.3F;

        // Gentle whole-body idle sway (current drift simulation)
        this.swing(body, idleSpeed * 0.6F, idleDegree * 0.3F, false, 0F, 0F, ageInTicks, idleAmount);
        this.swing(tail, idleSpeed * 0.6F, idleDegree * 0.5F, false, -1.0F, 0F, ageInTicks, idleAmount);
        this.swing(tail_fin, idleSpeed * 0.6F, idleDegree * 0.7F, false, -2.0F, 0F, ageInTicks, idleAmount);
        this.swing(head, idleSpeed * 0.6F, idleDegree * 0.15F, true, 1.0F, 0F, ageInTicks, idleAmount);

        // Idle pectoral fin rippling — gentle, independent wave motion
        this.flap(left_fin, idleSpeed, idleDegree * 0.4F, false, 3.5F, -0.1F, ageInTicks, idleAmount);
        this.flap(right_fin, idleSpeed, idleDegree * 0.4F, true, 3.5F, -0.1F, ageInTicks, idleAmount);
        // Asymmetric micro-adjustments for natural feel
        this.flap(left_fin, idleSpeed * 1.3F, idleDegree * 0.15F, false, 5.0F, 0F, ageInTicks, idleAmount);
        this.flap(right_fin, idleSpeed * 1.3F, idleDegree * 0.15F, true, 5.5F, 0F, ageInTicks, idleAmount);

        // ── AAA BARBEL PHYSICS ──────────────────────────────────────
        // Barbels are sensory organs with fluid drag behavior
        // During swimming: sweep backward with delayed response
        // During idle: independent probing motion
        float swimDrag = inWater ? limbSwingAmount * 0.6F : 0F;

        // Big whiskers (maxillary barbels) — primary sensory probes
        // Sweep back during swimming
        left_BigWhisker.rotateAngleY -= swimDrag * Mth.sin(limbSwing * swimSpeed + 0.5F) * 0.35F;
        right_BigWhisker.rotateAngleY += swimDrag * Mth.sin(limbSwing * swimSpeed + 0.5F) * 0.35F;
        left_BigWhisker.rotateAngleX += swimDrag * Mth.abs(Mth.cos(limbSwing * swimSpeed)) * 0.1F;
        right_BigWhisker.rotateAngleX += swimDrag * Mth.abs(Mth.cos(limbSwing * swimSpeed)) * 0.1F;

        // Idle probing — each barbel moves independently
        this.swing(left_BigWhisker, idleSpeed, idleDegree * 0.9F, false, 2.0F, 0.15F, ageInTicks, idleAmount);
        this.swing(right_BigWhisker, idleSpeed, idleDegree * 0.9F, true, 2.2F, 0.15F, ageInTicks, idleAmount);
        // Vertical probing component
        this.flap(left_BigWhisker, idleSpeed * 0.7F, idleDegree * 0.5F, false, 3.0F, 0.1F, ageInTicks, idleAmount);
        this.flap(right_BigWhisker, idleSpeed * 0.7F, idleDegree * 0.5F, true, 2.8F, 0.1F, ageInTicks, idleAmount);

        // Small whiskers (mandibular barbels) — shorter, faster probing
        this.swing(left_SmallWhisker, idleSpeed * 1.4F, idleDegree * 0.7F, false, 3.0F, 0.1F, ageInTicks, idleAmount);
        this.swing(right_SmallWhisker, idleSpeed * 1.4F, idleDegree * 0.7F, true, 3.3F, 0.1F, ageInTicks, idleAmount);
        this.flap(left_SmallWhisker, idleSpeed * 1.1F, idleDegree * 0.4F, false, 4.0F, 0.05F, ageInTicks, idleAmount);
        this.flap(right_SmallWhisker, idleSpeed * 1.1F, idleDegree * 0.4F, true, 4.2F, 0.05F, ageInTicks, idleAmount);
        // Swim drag on small whiskers too
        left_SmallWhisker.rotateAngleY -= swimDrag * Mth.sin(limbSwing * swimSpeed + 0.7F) * 0.25F;
        right_SmallWhisker.rotateAngleY += swimDrag * Mth.sin(limbSwing * swimSpeed + 0.7F) * 0.25F;

        // ── AAA FEEDING / SPITTING ANIMATION ───────────────────────
        // Large catfish creates suction then forcefully expels
        // Spit: mouth opens wide → body recoils backward → mouth snaps shut
        if (spitProgress > 0.01F) {
            float spitCurve = Mth.sin(spitProgress * Mth.PI); // 0→1→0 bell curve
            // Mouth gape: head rotates down (ventral mouth opens)
            head.rotateAngleX += spitCurve * 0.45F;
            // Body recoil backward
            head.rotationPointZ += spitCurve * 1.8F;
            // Throat/body expansion (buccal cavity)
            body.setScale(body.getScaleX() + spitCurve * 0.08F,
                          body.getScaleY() + spitCurve * 0.06F,
                          body.getScaleZ());
            // Fins flare during spit for stability
            left_fin.rotateAngleZ += spitCurve * 0.25F;
            right_fin.rotateAngleZ -= spitCurve * 0.25F;
            // Tail fin stiffens briefly
            tail_fin.rotateAngleY += Mth.sin(spitCurve * Mth.PI * 2) * 0.1F;
        }

        // ── AAA SWALLOWED ENTITY BELLY BULGE ────────────────────────
        // When large catfish has swallowed something, belly visibly distends
        if (swallowedProgress > 0.01F) {
            body.setScale(body.getScaleX(),
                          body.getScaleY() + swallowedProgress * 0.18F,
                          body.getScaleZ() + swallowedProgress * 0.12F);
            // Heavier body moves slightly lower in water
            body.rotationPointY -= swallowedProgress * 0.8F;
            // Fins work harder to compensate for extra weight
            float extraFinWork = swallowedProgress * 0.3F;
            left_fin.rotateAngleZ += Mth.sin(ageInTicks * 0.3F) * extraFinWork;
            right_fin.rotateAngleZ -= Mth.sin(ageInTicks * 0.3F) * extraFinWork;
        }

        // ── AAA LAND FLOPPING ───────────────────────────────────────
        if (!inWater) {
            // Desperate thrashing — rapid, erratic body convulsions
            float flopIntensity = 1.3F;
            float flopSpeed = 1.1F;
            this.swing(body, flopSpeed, flopIntensity, false, 0F, 0F, ageInTicks, 1.0F);
            this.swing(tail, flopSpeed, flopIntensity * 1.2F, false, -0.8F, 0F, ageInTicks, 1.0F);
            this.swing(tail_fin, flopSpeed, flopIntensity * 1.5F, false, -1.5F, 0F, ageInTicks, 1.0F);
            this.flap(body, flopSpeed * 0.8F, 0.25F, false, 1.0F, 0F, ageInTicks, 1.0F);
            this.flap(tail, flopSpeed * 0.8F, 0.35F, false, 0.5F, 0F, ageInTicks, 1.0F);
            // Fins spread wide — desperate attempt to move
            left_fin.rotateAngleZ += 0.35F;
            right_fin.rotateAngleZ -= 0.35F;
            left_fin.rotateAngleY += 0.2F;
            right_fin.rotateAngleY -= 0.2F;
            // Barbels droop limply
            left_BigWhisker.rotateAngleX += 0.25F;
            right_BigWhisker.rotateAngleX += 0.25F;
            left_SmallWhisker.rotateAngleX += 0.3F;
            right_SmallWhisker.rotateAngleX += 0.3F;
            // Dorsal fin erect — stress response
            dorsal_fin.rotateAngleX -= 0.2F;
        }

        // ── HEAD TRACKING ──────────────────────────────────────────
        this.faceTarget(netHeadYaw, headPitch, 1.0F, head);
    }

    @Override
    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of(root);
    }

    @Override
    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of(root, head, body, dorsal_fin, tail, left_fin, right_fin,
                left_BigWhisker, right_BigWhisker, left_SmallWhisker, right_SmallWhisker, tail_fin);
    }

    public void setRotationAngle(AdvancedModelBox AdvancedModelBox, float x, float y, float z) {
        AdvancedModelBox.rotateAngleX = x;
        AdvancedModelBox.rotateAngleY = y;
        AdvancedModelBox.rotateAngleZ = z;
    }

}
