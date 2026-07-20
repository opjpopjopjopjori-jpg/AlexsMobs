package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntityAlligatorSnappingTurtle;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;

public class ModelAlligatorSnappingTurtle extends AdvancedEntityModel<EntityAlligatorSnappingTurtle> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox body;
    private final AdvancedModelBox arm_left;
    private final AdvancedModelBox arm_right;
    private final AdvancedModelBox leg_left;
    private final AdvancedModelBox leg_right;
    private final AdvancedModelBox shell;
    private final AdvancedModelBox spikes_left;
    private final AdvancedModelBox spikes_right;
    private final AdvancedModelBox neck;
    private final AdvancedModelBox head;
    private final AdvancedModelBox head_inside;
    private final AdvancedModelBox jaw;
    private final AdvancedModelBox tail;

    public ModelAlligatorSnappingTurtle() {
        texWidth = 128;
        texHeight = 128;

        root = new AdvancedModelBox(this, "root");
        root.setPos(0.0F, 24.0F, 0.0F);


        body = new AdvancedModelBox(this, "body");
        body.setPos(0.0F, 0.0F, 0.0F);
        root.addChild(body);
        body.setTextureOffset(0, 22).addBox(-7.0F, -6.0F, -8.0F, 14.0F, 6.0F, 16.0F, 0.0F, false);

        arm_left = new AdvancedModelBox(this, "arm_left");
        arm_left.setPos(6.1F, -1.7F, -6.4F);
        body.addChild(arm_left);
        setRotationAngle(arm_left, 0.0F, 0.5672F, 0.0436F);
        arm_left.setTextureOffset(47, 45).addBox(-0.5F, -1.5F, -2.0F, 9.0F, 3.0F, 4.0F, 0.0F, false);

        arm_right = new AdvancedModelBox(this, "arm_right");
        arm_right.setPos(-6.1F, -1.7F, -6.4F);
        body.addChild(arm_right);
        setRotationAngle(arm_right, 0.0F, -0.5672F, -0.0436F);
        arm_right.setTextureOffset(47, 45).addBox(-8.5F, -1.5F, -2.0F, 9.0F, 3.0F, 4.0F, 0.0F, true);

        leg_left = new AdvancedModelBox(this, "leg_left");
        body.addChild(leg_left);
        leg_left.setPos(6.1F, -1.7F, 6.6F);
        setRotationAngle(leg_left, 0.0F, -0.6109F, 0.0436F);
        leg_left.setTextureOffset(45, 22).addBox(-0.5F, -1.5F, -3.0F, 8.0F, 3.0F, 5.0F, 0.0F, false);

        leg_right = new AdvancedModelBox(this, "leg_right");
        leg_right.setPos(-6.1F, -1.7F, 6.6F);
        body.addChild(leg_right);
        setRotationAngle(leg_right, 0.0F, 0.6109F, -0.0436F);
        leg_right.setTextureOffset(45, 22).addBox(-7.5F, -1.5F, -3.0F, 8.0F, 3.0F, 5.0F, 0.0F, true);

        shell = new AdvancedModelBox(this, "shell");
        shell.setPos(0.0F, -6.0F, 0.0F);
        body.addChild(shell);
        shell.setTextureOffset(0, 0).addBox(-8.0F, -1.0F, -9.0F, 16.0F, 3.0F, 18.0F, 0.0F, false);

        spikes_left = new AdvancedModelBox(this, "spikes_left");
        spikes_left.setPos(4.0F, -2.0F, 0.0F);
        shell.addChild(spikes_left);
        spikes_left.setTextureOffset(0, 45).addBox(-4.0F, -1.0F, -8.0F, 7.0F, 2.0F, 16.0F, 0.0F, false);

        spikes_right = new AdvancedModelBox(this, "spikes_right");
        spikes_right.setPos(-4.0F, -2.0F, 0.0F);
        shell.addChild(spikes_right);
        spikes_right.setTextureOffset(0, 45).addBox(-3.0F, -1.0F, -8.0F, 7.0F, 2.0F, 16.0F, 0.0F, true);

        neck = new AdvancedModelBox(this, "neck");
        neck.setPos(0.0F, -2.0F, -8.0F);
        body.addChild(neck);
        neck.setTextureOffset(51, 9).addBox(-3.5F, -3.0F, -3.0F, 7.0F, 5.0F, 3.0F, 0.0F, false);

        head = new AdvancedModelBox(this, "head");
        head.setPos(0.0F, -0.75F, -3.05F);
        neck.addChild(head);
        head.setTextureOffset(51, 0).addBox(-3.0F, -2.25F, -4.95F, 6.0F, 3.0F, 5.0F, 0.0F, false);

        head_inside = new AdvancedModelBox(this, "head_inside");
        head_inside.setPos(0.0F, 0F, 0F);
        head.addChild(head_inside);
        head_inside.setTextureOffset(73, 0).addBox(-3.0F, -2.25F, -4.95F, 6.0F, 3.0F, 5.0F, 0.0F, false);


        jaw = new AdvancedModelBox(this, "jaw");
        jaw.setPos(0.0F, 1.15F, 0.15F);
        head.addChild(jaw);
        setRotationAngle(jaw, -0.2182F, 0.0F, 0.0F);
        jaw.setTextureOffset(51, 53).addBox(-2.5F, -0.5F, -5.0F, 5.0F, 2.0F, 5.0F, 0.0F, false);

        tail = new AdvancedModelBox(this, "tail");
        tail.setPos(0.0F, -2.5F, 8.0F);
        body.addChild(tail);
        tail.setTextureOffset(31, 45).addBox(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 9.0F, 0.0F, false);
        this.updateDefaultPose();
    }

    @Override
    public void setupAnim(EntityAlligatorSnappingTurtle entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        //══════ 🐢 ALLIGATOR SNAPPING TURTLE — LURE-TONGUE AMBUSH PREDATOR ══════
        // IDENTITY: Ancient armored ambusher. Tongue is a WORM-SHAPED LURE
        // that wiggles to attract fish. Then EXPLOSIVE snap. Spiked shell.
        // UNIQUE vs Terrapin (gentle swimmer), WarpedToad (hopping tongue-shooter).

        // Biomechanical constants for heavy reptile physics & weight transfer
        float idleSpeed = 0.06F;
        float idleDegree = 0.2F;
        boolean inWater = entityIn.isInWater();
        float walkSpeed = inWater ? 0.45F : 0.85F;
        float walkDegree = inWater ? 0.9F : 0.65F;
        float partialTicks = Minecraft.getInstance().getFrameTime();

        // Attack & Mouth Open progress interpolation
        float openProgress = entityIn.prevOpenMouthProgress + (entityIn.openMouthProgress - entityIn.prevOpenMouthProgress) * partialTicks;
        float snapProgress = entityIn.prevAttackProgress + (entityIn.attackProgress - entityIn.prevAttackProgress) * partialTicks;

        // Advanced AAA Jaw & Snap Mechanics with anticipation and recoil
        progressRotationPrev(neck, openProgress, Maths.rad(-12), 0, 0, 5F);
        progressRotationPrev(head, openProgress, Maths.rad(-40), 0, 0, 5F);
        progressRotationPrev(jaw, openProgress, Maths.rad(70), 0, 0, 5F);
        progressPositionPrev(jaw, openProgress, 0, -1.2F, 0, 5F);

        progressPositionPrev(neck, snapProgress, 0, 0, -1.5F, 5F);
        neck.setScale((1 - snapProgress * 0.05F), (1 - snapProgress * 0.05F), (1 + snapProgress * 0.6F));
        head.rotationPointZ -= 1.6F * snapProgress;
        progressRotationPrev(head, snapProgress, Maths.rad(15), 0, 0, 5F);
        progressRotationPrev(jaw, snapProgress, Maths.rad(-15), 0, 0, 5F);

        // Procedural Idle Breathing (Subtle chest and neck expansion reflecting massive reptile metabolism)
        float breathCycle = Maths.cos(ageInTicks * 0.08F);
        body.rotationPointY += breathCycle * 0.3F;
        shell.setScale(1.0F+breathCycle*0.01F,1.0F+breathCycle*0.01F,1.0F+breathCycle*0.01F);
        body.setScale(1.0F+breathCycle*0.015F,1.0F,1.0F+breathCycle*0.015F);
        neck.rotateAngleX += breathCycle * 0.03F;

        // Secondary Motion & Inertia: Tail follows body movement with phase lag
        this.swing(tail, idleSpeed, idleDegree * 1.2F, false, 2.5F, 0F, ageInTicks, 1.0F);
        this.bob(body, idleSpeed * 2.0F, idleDegree * 0.5F, false, ageInTicks, 1.0F);

        // Quadrupedal Heavy Locomotion (Accurate diagonal gait phase synchronization)
        if (limbSwingAmount > 0.01F) {
            // Heavy footfalls with proper weight transfer and torso roll
            this.swing(leg_right, walkSpeed, walkDegree, true, 1.0F, 0.1F, limbSwing, limbSwingAmount);
            this.swing(leg_left, walkSpeed, walkDegree, false, 1.0F, -0.1F, limbSwing, limbSwingAmount);
            this.swing(arm_right, walkSpeed, walkDegree, false, 0.0F, 0.1F, limbSwing, limbSwingAmount);
            this.swing(arm_left, walkSpeed, walkDegree, true, 0.0F, -0.1F, limbSwing, limbSwingAmount);

            // Vertical weight shifting (Body heave on step impact)
            this.bob(body, walkSpeed * 2.0F, walkDegree * 1.5F, true, limbSwing, limbSwingAmount);
            
            // Tail counter-steering during locomotion
            this.swing(tail, walkSpeed * 0.9F, walkDegree * 1.2F, false, 3.0F, 0F, limbSwing, limbSwingAmount);
            
            // Head and neck follow-through / inertia
            this.walk(neck, walkSpeed * 0.75F, walkDegree * 0.2F, false, -1.5F, 0F, limbSwing, limbSwingAmount);
            this.walk(head, walkSpeed * 0.75F, walkDegree * 0.15F, false, -2.0F, 0F, limbSwing, limbSwingAmount);
        }

        // Head stabilization and natural look constraints
        this.head.rotateAngleY += netHeadYaw * 0.4F * Mth.DEG_TO_RAD;
        this.head.rotateAngleX += headPitch * 0.4F * Mth.DEG_TO_RAD;
    }

    public void renderToBuffer(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        this.head_inside.setScale(0.99F, 0.99F, 0.99F);
        if (this.young) {
            this.head.setScale(1.5F, 1.5F, 1.5F);
            matrixStackIn.pushPose();
            matrixStackIn.scale(0.25F, 0.25F, 0.25F);
            matrixStackIn.translate(0.0D, 4.5, 0.125D);
            parts().forEach((p_228292_8_) -> {
                p_228292_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
            });
            matrixStackIn.popPose();
        } else {
            this.head.setScale(1F, 1F, 1F);
            matrixStackIn.pushPose();
            parts().forEach((p_228290_8_) -> {
                p_228290_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
            });
            matrixStackIn.popPose();
        }

    }

    @Override
    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of(root);
    }

    @Override
    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of(root, body, shell, spikes_left, spikes_right, neck, jaw, head, head_inside, leg_left, leg_right, arm_left, arm_right, tail);
    }


    public void setRotationAngle(AdvancedModelBox AdvancedModelBox, float x, float y, float z) {
        AdvancedModelBox.rotateAngleX = x;
        AdvancedModelBox.rotateAngleY = y;
        AdvancedModelBox.rotateAngleZ = z;
    }
}
