package com.github.alexthe666.alexsmobs.client.model;// Made with Blockbench 3.7.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


import com.github.alexthe666.alexsmobs.entity.EntitySunbird;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;

public class ModelSunbird extends AdvancedEntityModel<EntitySunbird> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox body;
    private final AdvancedModelBox neck;
    private final AdvancedModelBox head;
    private final AdvancedModelBox hair;
    private final AdvancedModelBox left_wing;
    private final AdvancedModelBox left_wing1;
    private final AdvancedModelBox left_wing2;
    private final AdvancedModelBox right_wing;
    private final AdvancedModelBox right_wing1;
    private final AdvancedModelBox right_wing2;
    private final AdvancedModelBox tail1;
    private final AdvancedModelBox tail2;
    private final AdvancedModelBox left_leg;
    private final AdvancedModelBox left_foot;
    private final AdvancedModelBox right_leg;
    private final AdvancedModelBox right_foot;

    public ModelSunbird() {
        texWidth = 256;
        texHeight = 256;

        root = new AdvancedModelBox(this, "root");
        root.setRotationPoint(0.0F, 24.0F, 0.0F);


        body = new AdvancedModelBox(this, "body");
        body.setRotationPoint(0.0F, -13.0F, 0.0F);
        root.addChild(body);
        body.setTextureOffset(106, 38).addBox(-7.0F, -5.0F, -11.0F, 14.0F, 12.0F, 23.0F, 0.0F, false);

        neck = new AdvancedModelBox(this, "neck");
        neck.setRotationPoint(0.0F, -1.0F, -12.0F);
        body.addChild(neck);
        neck.setTextureOffset(0, 38).addBox(-3.0F, -3.0F, -12.0F, 6.0F, 6.0F, 13.0F, 0.0F, false);

        head = new AdvancedModelBox(this, "head");
        head.setRotationPoint(0.0F, 1.0F, -13.0F);
        neck.addChild(head);
        head.setTextureOffset(0, 0).addBox(-4.0F, -5.0F, -7.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
        head.setTextureOffset(12, 17).addBox(-2.0F, -2.0F, -12.0F, 4.0F, 3.0F, 5.0F, 0.0F, false);
        head.setTextureOffset(0, 0).addBox(-1.0F, 1.0F, -12.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);

        hair = new AdvancedModelBox(this, "hair");
        hair.setRotationPoint(0.0F, -5.0F, 0.0F);
        head.addChild(hair);
        hair.setTextureOffset(0, 17).addBox(0.0F, -8.0F, -9.0F, 0.0F, 8.0F, 11.0F, 0.0F, false);

        left_wing = new AdvancedModelBox(this, "left_wing");
        left_wing.setRotationPoint(8.0F, -3.0F, -8.0F);
        body.addChild(left_wing);
        left_wing.setTextureOffset(0, 119).addBox(-1.0F, -3.0F, -5.2F, 15.0F, 5.0F, 6.0F, 0.0F, false);

        left_wing1 = new AdvancedModelBox(this, "left_wing1");
        left_wing1.setRotationPoint(0.0F, -1.0F, 0.0F);
        left_wing.addChild(left_wing1);
        left_wing1.setTextureOffset(103, 80).addBox(-1.0F, 0.0F, -8.0F, 33.0F, 0.0F, 37.0F, 0.0F, false);

        left_wing2 = new AdvancedModelBox(this, "left_wing2");
        left_wing2.setRotationPoint(32.0F, 0.0F, 0.0F);
        left_wing1.addChild(left_wing2);
        left_wing2.setTextureOffset(0, 0).addBox(0.0F, 0.0F, -8.0F, 50.0F, 0.0F, 37.0F, 0.0F, false);

        right_wing = new AdvancedModelBox(this, "right_wing");
        right_wing.setRotationPoint(-8.0F, -3.0F, -8.0F);
        body.addChild(right_wing);
        right_wing.setTextureOffset(0, 119).addBox(-14.0F, -3.0F, -5.2F, 15.0F, 5.0F, 6.0F, 0.0F, true);

        right_wing1 = new AdvancedModelBox(this, "right_wing1");
        right_wing1.setRotationPoint(0.0F, -1.0F, 0.0F);
        right_wing.addChild(right_wing1);
        right_wing1.setTextureOffset(103, 80).addBox(-32.0F, 0.0F, -8.0F, 33.0F, 0.0F, 37.0F, 0.0F, true);

        right_wing2 = new AdvancedModelBox(this, "right_wing2");
        right_wing2.setRotationPoint(-32.0F, 0.0F, 0.0F);
        right_wing1.addChild(right_wing2);
        right_wing2.setTextureOffset(0, 0).addBox(-50.0F, 0.0F, -8.0F, 50.0F, 0.0F, 37.0F, 0.0F, true);

        tail1 = new AdvancedModelBox(this, "tail1");
        tail1.setRotationPoint(0.0F, -5.0F, 12.0F);
        body.addChild(tail1);
        tail1.setTextureOffset(0, 80).addBox(-23.0F, 0.0F, 0.0F, 32.0F, 0.0F, 38.0F, 0.0F, false);

        tail2 = new AdvancedModelBox(this, "tail2");
        tail2.setRotationPoint(-6.0F, 0.0F, 38.0F);
        tail1.addChild(tail2);
        tail2.setTextureOffset(0, 38).addBox(-16.0F, 0.0F, 0.0F, 32.0F, 0.0F, 41.0F, 0.0F, false);

        left_leg = new AdvancedModelBox(this, "left_leg");
        left_leg.setRotationPoint(3.0F, 8.0F, 8.0F);
        body.addChild(left_leg);
        left_leg.setTextureOffset(0, 58).addBox(-2.0F, -1.0F, -5.0F, 5.0F, 4.0F, 8.0F, 0.0F, false);

        left_foot = new AdvancedModelBox(this, "left_foot");
        left_foot.setRotationPoint(0.5F, 3.0F, -2.0F);
        left_leg.addChild(left_foot);
        setRotationAngle(left_foot, 0.0436F, 0.0F, 0.0F);
        left_foot.setTextureOffset(22, 66).addBox(-2.0F, 0.0F, -5.0F, 4.0F, 3.0F, 5.0F, 0.0F, false);

        right_leg = new AdvancedModelBox(this, "right_leg");
        right_leg.setRotationPoint(-3.0F, 8.0F, 8.0F);
        body.addChild(right_leg);
        right_leg.setTextureOffset(0, 58).addBox(-3.0F, -1.0F, -5.0F, 5.0F, 4.0F, 8.0F, 0.0F, true);

        right_foot = new AdvancedModelBox(this, "right_foot");
        right_foot.setRotationPoint(-0.5F, 3.0F, -2.0F);
        right_leg.addChild(right_foot);
        setRotationAngle(right_foot, 0.0436F, 0.0F, 0.0F);
        right_foot.setTextureOffset(22, 66).addBox(-2.0F, 0.0F, -5.0F, 4.0F, 3.0F, 5.0F, 0.0F, true);
        this.updateDefaultPose();
    }

    @Override
    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of(root);
    }

    @Override
    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of(root, hair, body, tail1, tail2, left_wing, left_wing1, left_wing2, right_wing, right_wing1, right_wing2, left_leg, right_leg, right_foot, left_foot, neck, head);
    }

    @Override
    public void setupAnim(EntitySunbird entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        float flySpeed = 0.45F;
        float flyDegree = 0.6F;
        float hoverSpeed = 0.8F;
        float hoverDegree = 0.25F;
        float tailFanSpeed = 0.15F;
        float tailFanDegree = 0.3F;

        //══════ 🔥 SUNBIRD — HOVERING PHOENIX ══════
        // IDENTITY: Hovers like a hummingbird with RAPID wing beats.
        // Tail fans dramatically (like peacock). Fire-bird energy.
        // Head darts quickly. Can hover in place.
        // UNIQUE from Bald Eagle: Eagle = slow soaring. Sunbird = rapid hovering.
        // Eagle = wide majestic. Sunbird = compact energetic fire.

        float partialTick = Minecraft.getInstance().getFrameTime();
        float birdPitch = entityIn.prevBirdPitch + (entityIn.birdPitch - entityIn.prevBirdPitch) * partialTick;

        // ── BREATHING: fast metabolic fire-bird ──
        float breath=Mth.cos(ageInTicks*0.22F);
        body.setScale(1.0F,1.0F+breath*0.02F,1.0F);
        body.rotationPointY+=breath*0.08F;

        // ── HOVERING WINGS: figure-8 pattern, ultra-fast ──
        // Unlike eagle (wide slow flaps), sunbird wings beat in tight figure-8
        this.flap(right_wing, hoverSpeed, hoverDegree, false, 0F, 0F, ageInTicks, 1);
        this.flap(left_wing, hoverSpeed, hoverDegree, true, 0F, 0F, ageInTicks, 1);
        // Wing tips have independent flutter (feather ripple at tips)
        this.flap(right_wing2, hoverSpeed*1.3F, hoverDegree*0.6F, false, -1.2F, 0F, ageInTicks, 1);
        this.flap(left_wing2, hoverSpeed*1.3F, hoverDegree*0.6F, true, -1.2F, 0F, ageInTicks, 1);
        // Secondary wingtip micro-flutter (fire flicker effect)
        this.flap(left_wing2, 0.45F, 0.04F, true, 0.5F, 0, ageInTicks, 1);
        this.flap(right_wing2, 0.45F, 0.04F, false, 0.5F, 0, ageInTicks, 1);

        // ── TAIL: dramatic fan display (peacock-like) ──
        // Tail fans open and close in slow dramatic rhythm
        this.swing(tail1, tailFanSpeed, tailFanDegree, false, 1F, 0F, ageInTicks, 1);
        this.swing(tail2, tailFanSpeed*1.3F, tailFanDegree*1.2F, false, 0.5F, 0F, ageInTicks, 1);
        // Tail ripples during flight
        this.walk(tail1, flySpeed*0.5F, flyDegree*0.15F, false, 1F, 0F, limbSwing, limbSwingAmount);
        this.flap(tail2, 0.2F, 0.06F, false, 1F, 0, ageInTicks, 1);

        // ── HEAD: quick darting (investigates flowers/prey) ──
        // Unlike eagle (slow scanning), sunbird darts head quickly
        head.rotateAngleX+=Mth.sin(ageInTicks*0.5F)*0.1F;
        head.rotateAngleZ+=Mth.sin(ageInTicks*0.6F+1F)*0.08F;

        // ── HAIR/CREST: fire-like flicker ──
        if(hair!=null){
            this.flap(hair, 0.35F, 0.06F, false, 0F, 0, ageInTicks, 1);
            hair.rotateAngleX+=Mth.sin(ageInTicks*0.4F)*0.08F;
        }

        // ── BODY: gentle hover bob ──
        this.bob(body, hoverSpeed*0.3F, hoverDegree*2F, false, ageInTicks, 1);

        // ── LEGS: tuck during flight, extend for landing ──
        this.walk(left_leg, flySpeed*0.5F, flyDegree*0.15F, false, 3F, 0F, limbSwing, limbSwingAmount);
        this.walk(right_leg, flySpeed*0.5F, flyDegree*0.15F, false, 3F, 0F, limbSwing, limbSwingAmount);
        // Feet dangle slightly during hover
        left_foot.rotateAngleX+=Mth.sin(ageInTicks*0.3F)*0.05F;
        right_foot.rotateAngleX+=Mth.sin(ageInTicks*0.3F+1F)*0.05F;

        this.body.rotateAngleX += birdPitch * Mth.DEG_TO_RAD;
        this.faceTarget(netHeadYaw, headPitch, 1, neck, head);
    }

    public void setRotationAngle(AdvancedModelBox AdvancedModelBox, float x, float y, float z) {
        AdvancedModelBox.rotateAngleX = x;
        AdvancedModelBox.rotateAngleY = y;
        AdvancedModelBox.rotateAngleZ = z;
    }
}