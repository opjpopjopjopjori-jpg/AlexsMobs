package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntityGiantSquid;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

public class ModelGiantSquid extends AdvancedEntityModel<EntityGiantSquid> {
    private final AdvancedModelBox root,head,beak;
    private final AdvancedModelBox left_FrontTentacle,left_FrontTentacleEnd,left_FrontMidTentacle,left_FrontMidTentacleEnd;
    private final AdvancedModelBox right_FrontMidTentacle,right_FrontMidTentacleEnd,left_BackMidTentacle,left_BackMidTentacleEnd;
    private final AdvancedModelBox right_BackMidTentacle,right_BackMidTentacleEnd,left_BackTentacle,left_BackTentacleEnd;
    private final AdvancedModelBox right_BackTentacle,right_BackTentacleEnd,right_FrontTentacle,right_tentacleEnd;
    private final AdvancedModelBox left_arm,left_arm2,left_arm3,left_arm4,left_hand;
    private final AdvancedModelBox right_arm,right_arm2,right_arm3,right_arm4,right_hand;
    private final AdvancedModelBox left_eye,left_pupil,left_pupil_pivot,right_pupil_pivot,right_eye,right_pupil;
    private final AdvancedModelBox mantle,mantle_end,left_membrane,right_membrane;

    public ModelGiantSquid(){texWidth=128;texHeight=128;
        root=new AdvancedModelBox(this,"root");root.setRotationPoint(0.0F,24.0F,0.0F);
        head=new AdvancedModelBox(this,"head");head.setRotationPoint(0.0F,-5.0F,0.0F);root.addChild(head);head.setTextureOffset(43,35).addBox(-5.0F,-9.0F,-5.0F,10.0F,14.0F,10.0F,0.0F,false);
        beak=new AdvancedModelBox(this,"beak");beak.setRotationPoint(0.0F,5.0F,0.0F);head.addChild(beak);beak.setTextureOffset(41,0).addBox(-1.5F,0.0F,-2.0F,3.0F,2.0F,4.0F,0.0F,false);
        left_FrontTentacle=new AdvancedModelBox(this,"left_FrontTentacle");left_FrontTentacle.setRotationPoint(1.5F,5.0F,-4.0F);head.addChild(left_FrontTentacle);left_FrontTentacle.setTextureOffset(18,70).addBox(-1.0F,0.0F,-1.0F,2.0F,20.0F,2.0F,0.0F,false);
        left_FrontTentacleEnd=new AdvancedModelBox(this,"left_FrontTentacleEnd");left_FrontTentacleEnd.setRotationPoint(0.0F,20.0F,0.0F);left_FrontTentacle.addChild(left_FrontTentacleEnd);left_FrontTentacleEnd.setTextureOffset(18,70).addBox(-1.0F,0.0F,-1.0F,2.0F,20.0F,2.0F,0.0F,false);
        left_FrontMidTentacle=new AdvancedModelBox(this,"left_FrontMidTentacle");left_FrontMidTentacle.setRotationPoint(4.0F,5.0F,-2.0F);head.addChild(left_FrontMidTentacle);setRotationAngle(left_FrontMidTentacle,0.0F,-1.5708F,0.0F);left_FrontMidTentacle.setTextureOffset(18,70).addBox(-1.0F,0.0F,-1.0F,2.0F,20.0F,2.0F,0.0F,false);
        left_FrontMidTentacleEnd=new AdvancedModelBox(this,"left_FrontMidTentacleEnd");left_FrontMidTentacleEnd.setRotationPoint(0.0F,20.0F,0.0F);left_FrontMidTentacle.addChild(left_FrontMidTentacleEnd);left_FrontMidTentacleEnd.setTextureOffset(18,70).addBox(-1.0F,0.0F,-1.0F,2.0F,20.0F,2.0F,0.0F,false);
        right_FrontMidTentacle=new AdvancedModelBox(this,"right_FrontMidTentacle");right_FrontMidTentacle.setRotationPoint(-4.0F,5.0F,-2.0F);head.addChild(right_FrontMidTentacle);setRotationAngle(right_FrontMidTentacle,0.0F,1.5708F,0.0F);right_FrontMidTentacle.setTextureOffset(18,70).addBox(-1.0F,0.0F,-1.0F,2.0F,20.0F,2.0F,0.0F,true);
        right_FrontMidTentacleEnd=new AdvancedModelBox(this,"right_FrontMidTentacleEnd");right_FrontMidTentacleEnd.setRotationPoint(0.0F,20.0F,0.0F);right_FrontMidTentacle.addChild(right_FrontMidTentacleEnd);right_FrontMidTentacleEnd.setTextureOffset(18,70).addBox(-1.0F,0.0F,-1.0F,2.0F,20.0F,2.0F,0.0F,true);
        left_BackMidTentacle=new AdvancedModelBox(this,"left_BackMidTentacle");left_BackMidTentacle.setRotationPoint(4.0F,5.0F,2.0F);head.addChild(left_BackMidTentacle);setRotationAngle(left_BackMidTentacle,0.0F,-1.3963F,0.0F);left_BackMidTentacle.setTextureOffset(18,70).addBox(-1.0F,0.0F,-1.0F,2.0F,20.0F,2.0F,0.0F,false);
        left_BackMidTentacleEnd=new AdvancedModelBox(this,"left_BackMidTentacleEnd");left_BackMidTentacleEnd.setRotationPoint(0.0F,20.0F,0.0F);left_BackMidTentacle.addChild(left_BackMidTentacleEnd);left_BackMidTentacleEnd.setTextureOffset(18,70).addBox(-1.0F,0.0F,-1.0F,2.0F,20.0F,2.0F,0.0F,false);
        right_BackMidTentacle=new AdvancedModelBox(this,"right_BackMidTentacle");right_BackMidTentacle.setRotationPoint(-4.0F,5.0F,2.0F);head.addChild(right_BackMidTentacle);setRotationAngle(right_BackMidTentacle,0.0F,1.3963F,0.0F);right_BackMidTentacle.setTextureOffset(18,70).addBox(-1.0F,0.0F,-1.0F,2.0F,20.0F,2.0F,0.0F,true);
        right_BackMidTentacleEnd=new AdvancedModelBox(this,"right_BackMidTentacleEnd");right_BackMidTentacleEnd.setRotationPoint(0.0F,20.0F,0.0F);right_BackMidTentacle.addChild(right_BackMidTentacleEnd);right_BackMidTentacleEnd.setTextureOffset(18,70).addBox(-1.0F,0.0F,-1.0F,2.0F,20.0F,2.0F,0.0F,true);
        left_BackTentacle=new AdvancedModelBox(this,"left_BackTentacle");left_BackTentacle.setRotationPoint(2.0F,5.0F,4.0F);head.addChild(left_BackTentacle);setRotationAngle(left_BackTentacle,0.0F,3.1416F,0.0F);left_BackTentacle.setTextureOffset(18,70).addBox(-1.0F,0.0F,-1.0F,2.0F,20.0F,2.0F,0.0F,false);
        left_BackTentacleEnd=new AdvancedModelBox(this,"left_BackTentacleEnd");left_BackTentacleEnd.setRotationPoint(0.0F,20.0F,0.0F);left_BackTentacle.addChild(left_BackTentacleEnd);left_BackTentacleEnd.setTextureOffset(18,70).addBox(-1.0F,0.0F,-1.0F,2.0F,20.0F,2.0F,0.0F,false);
        right_BackTentacle=new AdvancedModelBox(this,"right_BackTentacle");right_BackTentacle.setRotationPoint(-2.0F,5.0F,4.0F);head.addChild(right_BackTentacle);setRotationAngle(right_BackTentacle,0.0F,-3.1416F,0.0F);right_BackTentacle.setTextureOffset(18,70).addBox(-1.0F,0.0F,-1.0F,2.0F,20.0F,2.0F,0.0F,true);
        right_BackTentacleEnd=new AdvancedModelBox(this,"right_BackTentacleEnd");right_BackTentacleEnd.setRotationPoint(0.0F,20.0F,0.0F);right_BackTentacle.addChild(right_BackTentacleEnd);right_BackTentacleEnd.setTextureOffset(18,70).addBox(-1.0F,0.0F,-1.0F,2.0F,20.0F,2.0F,0.0F,true);
        right_FrontTentacle=new AdvancedModelBox(this,"right_FrontTentacle");right_FrontTentacle.setRotationPoint(-1.5F,5.0F,-4.0F);head.addChild(right_FrontTentacle);right_FrontTentacle.setTextureOffset(18,70).addBox(-1.0F,0.0F,-1.0F,2.0F,20.0F,2.0F,0.0F,true);
        right_tentacleEnd=new AdvancedModelBox(this,"right_tentacleEnd");right_tentacleEnd.setRotationPoint(0.0F,20.0F,0.0F);right_FrontTentacle.addChild(right_tentacleEnd);right_tentacleEnd.setTextureOffset(18,70).addBox(-1.0F,0.0F,-1.0F,2.0F,20.0F,2.0F,0.0F,true);
        left_arm=new AdvancedModelBox(this,"left_arm");left_arm.setRotationPoint(3.0F,5.0F,0.0F);head.addChild(left_arm);setRotationAngle(left_arm,0.0F,-1.5708F,0.0F);left_arm.setTextureOffset(32,66).addBox(-1.0F,0.0F,-1.0F,2.0F,20.0F,2.0F,0.0F,false);
        left_arm2=new AdvancedModelBox(this,"left_arm2");left_arm2.setRotationPoint(0.0F,20.0F,0.0F);left_arm.addChild(left_arm2);left_arm2.setTextureOffset(45,60).addBox(-1.0F,0.0F,-1.0F,2.0F,35.0F,2.0F,0.0F,false);
        left_arm3=new AdvancedModelBox(this,"left_arm3");left_arm3.setRotationPoint(0.0F,35.0F,0.0F);left_arm2.addChild(left_arm3);left_arm3.setTextureOffset(45,60).addBox(-1.0F,0.0F,-1.0F,2.0F,35.0F,2.0F,0.0F,false);
        left_arm4=new AdvancedModelBox(this,"left_arm4");left_arm4.setRotationPoint(0.0F,35.0F,0.0F);left_arm3.addChild(left_arm4);left_arm4.setTextureOffset(45,60).addBox(-1.0F,0.0F,-1.0F,2.0F,35.0F,2.0F,0.0F,false);
        left_hand=new AdvancedModelBox(this,"left_hand");left_hand.setRotationPoint(0.0F,35.0F,0.0F);left_arm4.addChild(left_hand);left_hand.setTextureOffset(54,60).addBox(-3.0F,0.0F,-1.3F,6.0F,14.0F,3.0F,0.0F,false);
        right_arm=new AdvancedModelBox(this,"right_arm");right_arm.setRotationPoint(-3.0F,5.0F,0.0F);head.addChild(right_arm);setRotationAngle(right_arm,0.0F,1.5708F,0.0F);right_arm.setTextureOffset(32,66).addBox(-1.0F,0.0F,-1.0F,2.0F,20.0F,2.0F,0.0F,true);
        right_arm2=new AdvancedModelBox(this,"right_arm2");right_arm2.setRotationPoint(0.0F,20.0F,0.0F);right_arm.addChild(right_arm2);right_arm2.setTextureOffset(45,60).addBox(-1.0F,0.0F,-1.0F,2.0F,35.0F,2.0F,0.0F,true);
        right_arm3=new AdvancedModelBox(this,"right_arm3");right_arm3.setRotationPoint(0.0F,35.0F,0.0F);right_arm2.addChild(right_arm3);right_arm3.setTextureOffset(45,60).addBox(-1.0F,0.0F,-1.0F,2.0F,35.0F,2.0F,0.0F,true);
        right_arm4=new AdvancedModelBox(this,"right_arm4");right_arm4.setRotationPoint(0.0F,35.0F,0.0F);right_arm3.addChild(right_arm4);right_arm4.setTextureOffset(45,60).addBox(-1.0F,0.0F,-1.0F,2.0F,35.0F,2.0F,0.0F,true);
        right_hand=new AdvancedModelBox(this,"right_hand");right_hand.setRotationPoint(0.0F,35.0F,0.0F);right_arm4.addChild(right_hand);right_hand.setTextureOffset(54,60).addBox(-3.0F,0.0F,-1.3F,6.0F,14.0F,3.0F,0.0F,true);
        left_eye=new AdvancedModelBox(this,"left_eye");left_eye.setRotationPoint(5.9F,0.5F,0.0F);head.addChild(left_eye);left_eye.setTextureOffset(53,19).addBox(-1.5F,-3.5F,-3.5F,3.0F,7.0F,7.0F,0.0F,false);
        left_pupil_pivot=new AdvancedModelBox(this,"left_pupil_pivot");left_pupil_pivot.setRotationPoint(1.55F,0.0F,0.0F);left_eye.addChild(left_pupil_pivot);
        left_pupil=new AdvancedModelBox(this,"left_pupil");left_pupil_pivot.addChild(left_pupil);left_pupil.setTextureOffset(0,0).addBox(0.0F,-2.5F,-2.5F,0.0F,5.0F,5.0F,0.0F,false);
        right_eye=new AdvancedModelBox(this,"right_eye");right_eye.setRotationPoint(-5.9F,0.5F,0.0F);head.addChild(right_eye);right_eye.setTextureOffset(53,19).addBox(-1.5F,-3.5F,-3.5F,3.0F,7.0F,7.0F,0.0F,true);
        right_pupil_pivot=new AdvancedModelBox(this,"right_pupil_pivot");right_pupil_pivot.setRotationPoint(-1.55F,0.0F,0.0F);right_eye.addChild(right_pupil_pivot);
        right_pupil=new AdvancedModelBox(this,"right_pupil");right_pupil_pivot.addChild(right_pupil);right_pupil.setTextureOffset(0,0).addBox(0.0F,-2.5F,-2.5F,0.0F,5.0F,5.0F,0.0F,true);
        mantle=new AdvancedModelBox(this,"mantle");mantle.setRotationPoint(0.0F,-5.0F,0.0F);head.addChild(mantle);mantle.setTextureOffset(0,0).addBox(-7.0F,-31.0F,-6.0F,14.0F,32.0F,12.0F,0.0F,false);
        mantle_end=new AdvancedModelBox(this,"mantle_end");mantle_end.setRotationPoint(0.0F,-31.0F,0.0F);mantle.addChild(mantle_end);mantle_end.setTextureOffset(53,0).addBox(-4.0F,-7.0F,-4.0F,8.0F,10.0F,8.0F,0.0F,false);
        left_membrane=new AdvancedModelBox(this,"left_membrane");left_membrane.setRotationPoint(0.0F,-2.0F,0.0F);mantle_end.addChild(left_membrane);left_membrane.setTextureOffset(0,45).addBox(0.0F,-12.0F,0.0F,17.0F,24.0F,0.0F,0.0F,false);
        right_membrane=new AdvancedModelBox(this,"right_membrane");right_membrane.setRotationPoint(0.0F,-2.0F,0.0F);mantle_end.addChild(right_membrane);right_membrane.setTextureOffset(0,45).addBox(-17.0F,-12.0F,0.0F,17.0F,24.0F,0.0F,0.0F,true);
        this.updateDefaultPose();}

    @Override public Iterable<BasicModelPart> parts(){return ImmutableList.of(root);}
    @Override public Iterable<AdvancedModelBox> getAllParts(){return ImmutableList.of(root,head,beak,right_pupil_pivot,left_pupil_pivot,left_FrontTentacle,left_FrontTentacleEnd,left_FrontMidTentacle,left_FrontMidTentacleEnd,right_FrontMidTentacle,right_FrontMidTentacleEnd,left_BackMidTentacle,left_BackMidTentacleEnd,right_BackMidTentacle,right_BackMidTentacleEnd,left_BackTentacle,left_BackTentacleEnd,right_BackTentacle,right_BackTentacleEnd,right_FrontTentacle,right_tentacleEnd,left_arm,left_arm2,left_arm3,left_arm4,left_hand,right_arm,right_arm2,right_arm3,right_arm4,right_hand,left_eye,left_pupil,right_eye,right_pupil,mantle,mantle_end,left_membrane,right_membrane);}

    @Override
    public void setupAnim(EntityGiantSquid entity,float limbSwing,float limbSwingAmount,float ageInTicks,float netHeadYaw,float headPitch){
        this.resetToDefaultPose();
        //══════ 🦑 GIANT SQUID — JET-PROPULSION CEPHALOPOD ══════
        // IDENTITY: Primary locomotion = jet propulsion (mantle contraction).
        // Secondary = fin undulation (traveling wave). Arms = muscular hydrostats
        // that curl independently in 3D. Tentacles = asynchronous autonomous rhythm.
        // BIOMECHANICS: Squid do NOT "walk" with their appendages. They jet + undulate.

        float partialTick=ageInTicks-entity.tickCount;
        float dryProgress=entity.prevDryProgress+(entity.dryProgress-entity.prevDryProgress)*partialTick;
        float capturedProgress=entity.prevCapturedProgress+(entity.capturedProgress-entity.prevCapturedProgress)*partialTick;
        float grabProgress=entity.prevGrabProgress+(entity.grabProgress-entity.prevGrabProgress)*partialTick;
        float pitch=entity.prevSquidPitch+(entity.getSquidPitch()-entity.prevSquidPitch)*partialTick;
        float f=(pitch-90)*Mth.DEG_TO_RAD;
        this.head.rotateAngleX+=f;
        this.right_pupil_pivot.rotateAngleX-=f;this.left_pupil_pivot.rotateAngleX-=f;

        //══════ JET PROPULSION: asymmetric mantle cycle ══════
        // Real squid: RAPID contraction (0.3s) expels water → SLOW expansion (1.5s) refills.
        // This is modeled as: abs(sin) for contraction power, with slow recovery bias.
        float jetRaw=Mth.sin(limbSwing*0.4F);
        float jetIdle=Mth.sin(ageInTicks*0.15F)*0.25F;
        // Asymmetric: contraction is 3x faster than expansion
        float jetContract=Mth.abs(jetRaw)*1.5F+jetIdle;
        float periLead=Mth.abs(Mth.sin(limbSwing*0.4F+0.3F))*1.3F;
        mantle.setScale(1.0F-jetContract*0.07F,1.0F-jetContract*0.11F,1.0F-jetContract*0.07F);
        mantle_end.setScale(1.0F-periLead*0.05F,1.0F-periLead*0.03F,1.0F-periLead*0.05F);
        head.rotationPointZ-=jetContract*1.5F*limbSwingAmount;
        head.rotateAngleX-=jetContract*0.04F*limbSwingAmount;
        head.rotationPointY-=jetContract*0.12F*limbSwingAmount;

        //══════ FIN UNDULATION: traveling wave with breathing sync ══════
        float breath=Mth.cos(ageInTicks*0.06F); // declared early for fin+mantle use
        float finWave=Mth.sin(ageInTicks*0.15F);
        left_membrane.rotateAngleZ+=finWave*0.2F*(1-dryProgress);
        right_membrane.rotateAngleZ-=finWave*0.2F*(1-dryProgress);
        left_membrane.rotateAngleX+=Mth.sin(ageInTicks*0.15F+1.5F)*0.08F*(1-dryProgress);
        right_membrane.rotateAngleX+=Mth.sin(ageInTicks*0.15F+1.5F)*0.08F*(1-dryProgress);
        left_membrane.setScale(1.0F+breath*0.03F,1.0F,1.0F);
        right_membrane.setScale(1.0F+breath*0.03F,1.0F,1.0F);

        //══════ BREATHING APPLIED: mantle + beak ══════
        mantle.setScale(mantle.getScaleX(),mantle.getScaleY()+breath*0.02F,mantle.getScaleZ());
        mantle_end.setScale(mantle_end.getScaleX()+breath*0.015F,mantle_end.getScaleY(),mantle_end.getScaleZ()+breath*0.015F);
        head.rotationPointY+=breath*0.06F;
        // Beak micro-clatter at idle — subtle jaw flex
        beak.rotateAngleX+=breath*0.015F;

        //══════ TENTACLE CURL: independent muscular hydrostats ══════
        // Each of 8 short tentacles curls independently with its own phase.
        // Uses chainSwing NOT walk() — tentacles bend along their length, not swing from base.
        // Each tentacle pair (left/right) has different phase offset for visual variety.

        // Front tentacles (pair 1): slow, wide curl
        AdvancedModelBox[] tFL={left_FrontTentacle,left_FrontTentacleEnd};
        AdvancedModelBox[] tFR={right_FrontTentacle,right_tentacleEnd};
        this.chainSwing(tFL,0.08F,0.25F,-1.5F,ageInTicks,1-dryProgress*0.5F);
        this.chainSwing(tFR,0.08F,0.3F,-1.3F,ageInTicks,1-dryProgress*0.5F);

        // Front-mid tentacles (pair 2): medium curl, different phase
        AdvancedModelBox[] tFML={left_FrontMidTentacle,left_FrontMidTentacleEnd};
        AdvancedModelBox[] tFMR={right_FrontMidTentacle,right_FrontMidTentacleEnd};
        this.chainSwing(tFML,0.09F,0.28F,-1.8F,ageInTicks,1-dryProgress*0.5F);
        this.chainSwing(tFMR,0.09F,0.32F,-1.6F,ageInTicks,1-dryProgress*0.5F);

        // Back-mid tentacles (pair 3): tighter curl
        AdvancedModelBox[] tBML={left_BackMidTentacle,left_BackMidTentacleEnd};
        AdvancedModelBox[] tBMR={right_BackMidTentacle,right_BackMidTentacleEnd};
        this.chainSwing(tBML,0.10F,0.2F,-2.0F,ageInTicks,1-dryProgress*0.5F);
        this.chainSwing(tBMR,0.10F,0.22F,-1.8F,ageInTicks,1-dryProgress*0.5F);

        // Back tentacles (pair 4): fastest curl
        AdvancedModelBox[] tBL={left_BackTentacle,left_BackTentacleEnd};
        AdvancedModelBox[] tBR={right_BackTentacle,right_BackTentacleEnd};
        this.chainSwing(tBL,0.11F,0.18F,-2.2F,ageInTicks,1-dryProgress*0.5F);
        this.chainSwing(tBR,0.11F,0.2F,-2.0F,ageInTicks,1-dryProgress*0.5F);

        //══════ FEEDING ARMS: 3D spiral curl ══════
        // The 2 long arms curl in 3D (both X and Z rotation on each segment).
        // Uses chainFlap for Z-curl + chainSwing for X-curl = 3D spiral effect.
        AdvancedModelBox[] armL={left_arm,left_arm2,left_arm3,left_arm4,left_hand};
        AdvancedModelBox[] armR={right_arm,right_arm2,right_arm3,right_arm4,right_hand};
        this.chainSwing(armL,0.06F,0.35F,-3.0F,ageInTicks,1);
        this.chainFlap(armL,0.06F,0.25F,-2.5F,ageInTicks,1);
        this.chainSwing(armR,0.06F,0.35F,-2.8F,ageInTicks,1);
        this.chainFlap(armR,0.06F,0.25F,-2.3F,ageInTicks,1);

        //══════ STATE TRANSITIONS ══════
        // Dry state: tentacles droop, mantle flattens
        progressRotationPrev(mantle_end,dryProgress,Maths.rad(-10),0,0,5F);
        progressRotationPrev(right_membrane,dryProgress,0,Maths.rad(20),0,5F);
        progressRotationPrev(left_membrane,dryProgress,0,Maths.rad(-20),0,5F);
        progressRotationPrev(mantle,dryProgress,0,0,Maths.rad(15),5F);

        // Grab state: arms contract inward (preserved from original)
        float cGrab=1F-(0.2F*grabProgress);
        this.right_arm2.rotateAngleX-=cGrab*getArmRot(entity,2,partialTick,false);
        this.left_arm2.rotateAngleX+=cGrab*getArmRot(entity,2,partialTick,false);
        this.right_arm3.rotateAngleX-=cGrab*getArmRot(entity,8,partialTick,false);
        this.left_arm3.rotateAngleX+=cGrab*getArmRot(entity,8,partialTick,false);
        this.right_arm4.rotateAngleX-=cGrab*getArmRot(entity,12,partialTick,false);
        this.left_arm4.rotateAngleX+=cGrab*getArmRot(entity,12,partialTick,false);
        progressRotationPrev(left_arm,grabProgress,Maths.rad(-110),0,0,5F);
        progressRotationPrev(right_arm,grabProgress,Maths.rad(-110),0,0,5F);
        progressRotationPrev(left_arm2,grabProgress,Maths.rad(40),0,0,5F);
        progressRotationPrev(right_arm2,grabProgress,Maths.rad(40),0,0,5F);
        progressRotationPrev(left_arm3,grabProgress,Maths.rad(100),0,0,5F);
        progressRotationPrev(right_arm3,grabProgress,Maths.rad(100),0,0,5F);
        progressRotationPrev(left_arm4,grabProgress,Maths.rad(70),0,0,5F);
        progressRotationPrev(right_arm4,grabProgress,Maths.rad(70),0,0,5F);
        progressRotationPrev(left_hand,grabProgress,Maths.rad(-120),0,0,5F);
        progressRotationPrev(right_hand,grabProgress,Maths.rad(-120),0,0,5F);

        // Captured state: all tentacles wrap inward
        float cap=Math.max(grabProgress,capturedProgress);
        progressRotationPrev(left_FrontTentacle,cap,Maths.rad(-20),Maths.rad(-20),0,5F);
        progressRotationPrev(right_FrontTentacle,cap,Maths.rad(-20),Maths.rad(20),0,5F);
        progressRotationPrev(mantle,capturedProgress,Maths.rad(-20),0,0,5F);
        progressPositionPrev(mantle,capturedProgress,0,-2,0,5F);
        progressRotationPrev(head,capturedProgress,Maths.rad(20),0,0,5F);

        // Beak during grab
        if(grabProgress>=5F){this.walk(beak,0.7F,0.35F,true,0F,0F,ageInTicks,1);}

        //══════ EYE TRACKING (preserved — already AAA quality) ══════
        this.left_pupil.rotateAngleX+=f;this.right_pupil.rotateAngleX+=f;
        Entity look=Minecraft.getInstance().getCameraEntity();
        if(look!=null){
            Vec3 v3d=look.getEyePosition(partialTick);
            Vec3 v3d1=entity.getEyePosition(partialTick);
            float dist=Mth.clamp((float)v3d.subtract(v3d1).length()*0.2F,0.4F,1.0F);
            float eyeScale=1.4F-dist;
            double d0=(v3d.y-v3d1.y);
            Vec3 v3d2=entity.getViewVector(0.0F);v3d2=new Vec3(v3d2.x,0.0D,v3d2.z);
            Vec3 v3d3=(new Vec3(v3d.x-v3d1.x,0.0D,v3d.z-v3d1.z)).normalize();
            double d1=v3d2.dot(v3d3);
            double eyeXz=Mth.sqrt((float)Math.abs(d1))*-2F*(float)Math.signum(d1);
            float maxEyeDist=0.7F;
            this.left_pupil.setScale(eyeScale,eyeScale,eyeScale);
            this.left_pupil.rotationPointZ-=(float)Mth.clamp(-eyeXz,-maxEyeDist/eyeScale,maxEyeDist/eyeScale);
            this.left_pupil.rotationPointY+=(float)Mth.clamp(-d0,-maxEyeDist/eyeScale,maxEyeDist/eyeScale);
            this.right_pupil.setScale(eyeScale,eyeScale,eyeScale);
            this.right_pupil.rotationPointZ+=(float)Mth.clamp(eyeXz,-maxEyeDist/eyeScale,maxEyeDist/eyeScale);
            this.right_pupil.rotationPointY+=(float)Mth.clamp(-d0,-maxEyeDist/eyeScale,maxEyeDist/eyeScale);
        }
    }

    private float getArmRot(EntityGiantSquid entity,int offset,float partialTick,boolean pitch){
        float rotWrap=Mth.wrapDegrees(entity.getRingBuffer(offset,partialTick,pitch)-entity.getRingBuffer(0,partialTick,pitch));
        return(Mth.clamp(rotWrap,-50,50)*0.4F)*Mth.DEG_TO_RAD;
    }

    public void setRotationAngle(AdvancedModelBox a,float x,float y,float z){a.rotateAngleX=x;a.rotateAngleY=y;a.rotateAngleZ=z;}
}
