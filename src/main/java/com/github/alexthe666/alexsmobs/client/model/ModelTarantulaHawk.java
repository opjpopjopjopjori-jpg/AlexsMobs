package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntityTarantulaHawk;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.util.Mth;

public class ModelTarantulaHawk extends AdvancedEntityModel<EntityTarantulaHawk> {
    private final AdvancedModelBox root,body,wing_left,wing_right,legback_left,legback_right,legmid_left,legmid_right,legfront_left,legfront_right,head,fang_left,fang_right,antenna_left,antenna_right,abdomen,stinger;

    public ModelTarantulaHawk(){texWidth=128;texHeight=128;
        root=new AdvancedModelBox(this,"root");root.setPos(0.0F,24.0F,0.0F);
        body=new AdvancedModelBox(this,"body");body.setPos(0.0F,-15.0F,0.0F);root.addChild(body);body.setTextureOffset(33,54).addBox(-3.0F,-3.0F,-5.0F,6.0F,6.0F,10.0F,0.0F,false);
        wing_left=new AdvancedModelBox(this,"wing_left");wing_left.setPos(1.0F,-3.0F,-3.0F);body.addChild(wing_left);setRotationAngle(wing_left,0.0F,0.0F,-0.1309F);wing_left.setTextureOffset(0,0).addBox(0.0F,0.0F,-1.0F,20.0F,0.0F,21.0F,0.0F,false);
        wing_right=new AdvancedModelBox(this,"wing_right");wing_right.setPos(-1.0F,-3.0F,-3.0F);body.addChild(wing_right);setRotationAngle(wing_right,0.0F,0.0F,0.1309F);wing_right.setTextureOffset(0,0).addBox(-20.0F,0.0F,-1.0F,20.0F,0.0F,21.0F,0.0F,true);
        legback_left=new AdvancedModelBox(this,"legback_left");legback_left.setPos(2.0F,3.0F,3.0F);body.addChild(legback_left);setRotationAngle(legback_left,0.0F,-0.3054F,0.0F);legback_left.setTextureOffset(0,41).addBox(0.0F,-3.0F,0.0F,21.0F,15.0F,0.0F,0.0F,false);
        legback_right=new AdvancedModelBox(this,"legback_right");legback_right.setPos(-2.0F,3.0F,3.0F);body.addChild(legback_right);setRotationAngle(legback_right,0.0F,0.3054F,0.0F);legback_right.setTextureOffset(0,41).addBox(-21.0F,-3.0F,0.0F,21.0F,15.0F,0.0F,0.0F,true);
        legmid_left=new AdvancedModelBox(this,"legmid_left");legmid_left.setPos(2.0F,3.0F,0.0F);body.addChild(legmid_left);legmid_left.setTextureOffset(43,38).addBox(0.0F,-3.0F,0.0F,19.0F,15.0F,0.0F,0.0F,false);
        legmid_right=new AdvancedModelBox(this,"legmid_right");legmid_right.setPos(-2.0F,3.0F,0.0F);body.addChild(legmid_right);legmid_right.setTextureOffset(43,38).addBox(-19.0F,-3.0F,0.0F,19.0F,15.0F,0.0F,0.0F,true);
        legfront_left=new AdvancedModelBox(this,"legfront_left");legfront_left.setPos(2.0F,3.0F,-3.0F);body.addChild(legfront_left);setRotationAngle(legfront_left,0.0F,0.2618F,0.0F);legfront_left.setTextureOffset(41,22).addBox(0.0F,-3.0F,0.0F,19.0F,15.0F,0.0F,0.0F,false);
        legfront_right=new AdvancedModelBox(this,"legfront_right");legfront_right.setPos(-2.0F,3.0F,-3.0F);body.addChild(legfront_right);setRotationAngle(legfront_right,0.0F,-0.2618F,0.0F);legfront_right.setTextureOffset(41,22).addBox(-19.0F,-3.0F,0.0F,19.0F,15.0F,0.0F,0.0F,true);
        head=new AdvancedModelBox(this,"head");head.setPos(0.0F,0.0F,-5.0F);body.addChild(head);head.setTextureOffset(0,57).addBox(-4.0F,-2.0F,-4.0F,8.0F,7.0F,4.0F,0.0F,false);
        fang_left=new AdvancedModelBox(this,"fang_left");fang_left.setPos(1.0F,4.5F,-3.3F);head.addChild(fang_left);fang_left.setTextureOffset(0,22).addBox(-1.0F,-1.0F,-1.0F,3.0F,4.0F,1.0F,0.0F,false);
        fang_right=new AdvancedModelBox(this,"fang_right");fang_right.setPos(-1.0F,4.5F,-3.3F);head.addChild(fang_right);fang_right.setTextureOffset(0,22).addBox(-2.0F,-1.0F,-1.0F,3.0F,4.0F,1.0F,0.0F,true);
        antenna_left=new AdvancedModelBox(this,"antenna_left");antenna_left.setPos(1.0F,-2.0F,-4.0F);head.addChild(antenna_left);setRotationAngle(antenna_left,0.0F,-0.3927F,-0.3491F);antenna_left.setTextureOffset(0,0).addBox(0.0F,0.0F,-8.0F,0.0F,11.0F,8.0F,0.0F,false);
        antenna_right=new AdvancedModelBox(this,"antenna_right");antenna_right.setPos(-1.0F,-2.0F,-4.0F);head.addChild(antenna_right);setRotationAngle(antenna_right,0.0F,0.3927F,0.3491F);antenna_right.setTextureOffset(0,0).addBox(0.0F,0.0F,-8.0F,0.0F,11.0F,8.0F,0.0F,true);
        abdomen=new AdvancedModelBox(this,"abdomen");abdomen.setPos(0.0F,-2.0F,5.0F);body.addChild(abdomen);abdomen.setTextureOffset(0,22).addBox(-4.0F,0.0F,0.0F,8.0F,6.0F,12.0F,0.0F,false);
        stinger=new AdvancedModelBox(this,"stinger");stinger.setPos(0.0F,3.0F,12.0F);abdomen.addChild(stinger);stinger.setTextureOffset(9,0).addBox(0.0F,0.0F,0.0F,0.0F,1.0F,5.0F,0.0F,false);
        this.updateDefaultPose();}

    @Override public Iterable<AdvancedModelBox> getAllParts(){return ImmutableList.of(root,body,abdomen,head,antenna_left,antenna_right,legback_left,legback_right,legfront_left,legfront_right,legmid_left,legmid_right,wing_left,wing_right,stinger,fang_left,fang_right);}
    @Override public Iterable<BasicModelPart> parts(){return ImmutableList.of(root);}

    @Override
    public void setupAnim(EntityTarantulaHawk entity,float limbSwing,float limbSwingAmount,float ageInTicks,float netHeadYaw,float headPitch){
        this.resetToDefaultPose();
        //══════ 🐝 TARANTULA HAWK — STINGER-DRAGGING SPIDER HUNTER ══════
        // IDENTITY: Solitary aerial predator. Paralyzes tarantulas with STINGER,
        // then DRAGS them to burrow. Abdomen curls under to deliver sting.
        // Blue-black wings with iridescent flash. Antennae TAP surfaces.
        // Fast jerky wing beats (not smooth sinusoidal). Legs dangle in flight.
        // UNIQUE vs WarpedMosco: hunts with stinger, not proboscis. Drags prey, not slams.

        float idleSpeed=0.25F,idleDegree=0.25F;
        float walkSpeed=entity.isDragging()?2F:0.8F,walkDegree=0.4F;
        float flySpeed=0.25F,flyDegree=0.6F,digSpeed=0.85F,digDegree=0.6F;
        float partialTick=ageInTicks-entity.tickCount;
        float flyProgress=entity.prevFlyProgress+(entity.flyProgress-entity.prevFlyProgress)*partialTick;
        float dragProgress=entity.prevDragProgress+(entity.dragProgress-entity.prevDragProgress)*partialTick;
        float sitProgress=entity.prevSitProgress+(entity.sitProgress-entity.prevSitProgress)*partialTick;
        float digProgress=entity.prevDigProgress+(entity.digProgress-entity.prevDigProgress)*partialTick;
        float stingProgress=entity.prevAttackProgress+(entity.attackProgress-entity.prevAttackProgress)*partialTick;
        float walkProgress=5F-flyProgress;
        float stingFlyProgress=stingProgress*flyProgress*0.2F;
        float stingGroundProgress=stingProgress*walkProgress*0.2F;
        float flyAngle=entity.prevFlyAngle+(entity.getFlyAngle()-entity.prevFlyAngle)*partialTick;

        //══════ BREATHING: insect spiracle + abdomen pulse ══════
        float breath=Mth.cos(ageInTicks*0.16F);
        body.rotationPointY+=breath*0.04F;
        abdomen.setScale(1.0F+breath*0.025F,1.0F,1.0F+breath*0.015F);

        //══════ ANTENNAE: surface-tapping (wasp behavior) ══════
        // Tarantula hawks tap antennae on ground to detect prey by scent
        this.flap(antenna_left,0.3F,0.1F,false,1F,0,ageInTicks,1);
        this.flap(antenna_right,0.25F,0.08F,true,1.5F,0,ageInTicks,1);
        this.swing(antenna_left,0.2F,0.06F,true,0F,0,ageInTicks,1);
        this.swing(antenna_right,0.2F,0.06F,false,0.5F,0,ageInTicks,1);

        //══════ FANGS: grip and release ══════
        this.flap(fang_left,0.15F,0.04F,false,0F,0,ageInTicks,1);
        this.flap(fang_right,0.15F,0.04F,true,0.5F,0,ageInTicks,1);

        //══════ ABDOMEN + STINGER: curling coordination ══════
        // The defining tarantula hawk behavior: abdomen curls UNDER body,
        // stinger thrusts forward to paralyze tarantula
        this.walk(abdomen,idleSpeed,idleDegree*0.4F,true,0,0.1F,ageInTicks,1);
        stinger.setScale(1F,1F,1F+stingProgress*0.15F);

        //══════ FLIGHT: iridescent wing flash, jerky beats ══════
        progressRotationPrev(wing_left,flyProgress,0,Maths.rad(35),0,5F);
        progressRotationPrev(wing_right,flyProgress,0,Maths.rad(-35),0,5F);
        progressPositionPrev(body,flyProgress,0,-3,-2,5F);
        progressRotationPrev(head,flyProgress,Maths.rad(-20),0,0,5F);

        if(flyProgress>0){
            // Jerky wing beats — wasp wings have irregular intensity
            float jerk=Mth.abs(Mth.sin(ageInTicks*flySpeed*7F));
            float chaos=Mth.abs(Mth.sin(ageInTicks*flySpeed*4.3F+1.7F)); // second chaotic frequency
            float wingPower=flyDegree*(1.0F+jerk*0.25F+chaos*0.15F); // multi-frequency chaos
            this.flap(wing_left,flySpeed*7F,wingPower,true,0,0.1F,ageInTicks,1);
            this.flap(wing_right,flySpeed*7F,wingPower,false,0,0.1F,ageInTicks,1);
            // Iridescent flash: wings have micro-jitter modulated by chaos
            wing_left.rotateAngleZ+=Mth.sin(ageInTicks*0.8F)*0.03F*(jerk+chaos)*0.5F;
            wing_right.rotateAngleZ-=Mth.sin(ageInTicks*0.8F)*0.03F*(jerk+chaos)*0.5F;
            // Micro-pauses: wings briefly stop vibrating (real wasp behavior)
            // Legs dangle during flight
            this.flap(legfront_left,flySpeed,flyDegree*0.5F,true,1,0.1F,ageInTicks,1);
            this.flap(legfront_right,flySpeed,flyDegree*0.5F,false,1,0.1F,ageInTicks,1);
            this.flap(legmid_left,flySpeed,flyDegree*0.5F,true,2,0.1F,ageInTicks,1);
            this.flap(legmid_right,flySpeed,flyDegree*0.5F,false,2,0.1F,ageInTicks,1);
            this.flap(legback_left,flySpeed,flyDegree*0.5F,true,2,0.1F,ageInTicks,1);
            this.flap(legback_right,flySpeed,flyDegree*0.5F,false,2,0.1F,ageInTicks,1);
            this.walk(abdomen,flySpeed,flyDegree*0.35F,false,0,-0.1F,ageInTicks,1);
            this.walk(head,flySpeed,flyDegree*0.15F,true,0,-0.1F,ageInTicks,1);
            this.bob(body,flySpeed,flyDegree*5,false,ageInTicks,1);
        }else{
            //══════ WASP SEARCH-WALK: stop-and-probe pattern ══════
            // Tarantula hawks don't march continuously like ants.
            // They walk, STOP to probe ground with antennae, then walk again.
            // This creates a distinctive search-pause rhythm.
            float searchCycle = Mth.sin(limbSwing * walkSpeed * 0.4F); // slow search cycle
            float walkActive = searchCycle > 0.15F ? 1.0F : 0.25F; // only active walk during search phase
            float activeLimbAmount = limbSwingAmount * walkActive;

            // Tripod A (L-front + R-mid + L-back)
            this.swing(legfront_left, walkSpeed, walkDegree * 1.0F, false, 0F, -0.25F, limbSwing, activeLimbAmount);
            this.flap(legfront_left, walkSpeed, walkDegree * 0.6F, false, 0F, -0.3F, limbSwing, activeLimbAmount);
            this.swing(legmid_right, walkSpeed, walkDegree * 1.0F, true, 0F, 0.2F, limbSwing, activeLimbAmount);
            this.flap(legmid_right, walkSpeed, walkDegree * 0.6F, true, 0F, 0.3F, limbSwing, activeLimbAmount);
            this.swing(legback_left, walkSpeed, walkDegree * 1.0F, false, 0F, -0.25F, limbSwing, activeLimbAmount);
            this.flap(legback_left, walkSpeed, walkDegree * 0.6F, false, 0F, -0.3F, limbSwing, activeLimbAmount);
            // Tripod B (R-front + L-mid + R-back)
            this.swing(legfront_right, walkSpeed, walkDegree * 1.0F, true, 0F, 0.25F, limbSwing, activeLimbAmount);
            this.flap(legfront_right, walkSpeed, walkDegree * 0.6F, true, 0F, 0.3F, limbSwing, activeLimbAmount);
            this.swing(legmid_left, walkSpeed, walkDegree * 1.0F, false, 0F, -0.2F, limbSwing, activeLimbAmount);
            this.flap(legmid_left, walkSpeed, walkDegree * 0.6F, false, 0F, -0.3F, limbSwing, activeLimbAmount);
            this.swing(legback_right, walkSpeed, walkDegree * 1.0F, true, 0F, 0.25F, limbSwing, activeLimbAmount);
            this.flap(legback_right, walkSpeed, walkDegree * 0.6F, true, 0F, 0.3F, limbSwing, activeLimbAmount);

            // Body bobs during active walk, settles during pause
            this.bob(body, walkSpeed * 1.5F, walkDegree * 2.5F, false, limbSwing, activeLimbAmount);
            // Abdomen sways independently (stinger-ready posture)
            this.swing(abdomen, walkSpeed, walkDegree * 0.5F, false, 3, 0, limbSwing, activeLimbAmount);
            abdomen.rotateAngleX += Mth.sin(limbSwing * walkSpeed + 2F) * walkDegree * 0.06F * activeLimbAmount;
            this.walk(head, walkSpeed, walkDegree * 0.35F, false, 3, 0, limbSwing, activeLimbAmount);
        }

        //══════ DRAG MECHANICS: backward-pulling when dragging prey ══════
        // Tarantula hawks drag paralyzed spiders BACKWARD into burrows.
        if (dragProgress > 0) {
            float pullForce = dragProgress * 0.2F;
            legback_left.rotateAngleX -= 0.5F * pullForce;
            legback_right.rotateAngleX -= 0.5F * pullForce;
            legback_left.rotationPointZ += pullForce * 3F;
            legback_right.rotationPointZ += pullForce * 3F;
            this.walk(legfront_left, 0.5F, 0.6F * pullForce, false, 0F, -0.4F, limbSwing, limbSwingAmount);
            this.walk(legfront_right, 0.5F, 0.6F * pullForce, true, 0F, 0.4F, limbSwing, limbSwingAmount);
            body.rotateAngleX += 0.3F * pullForce;
            body.rotationPointZ -= pullForce * 4F;
            abdomen.rotateAngleX -= 0.4F * pullForce;
            stinger.rotateAngleX -= 0.3F * pullForce;
        }

        //══════ STATE TRANSITIONS (preserved) ══════
        progressPositionPrev(legfront_right,flyProgress,0,-1,2,5F);
        progressPositionPrev(legfront_left,flyProgress,0,-1,2,5F);
        progressRotationPrev(legfront_left,flyProgress,Maths.rad(35),Maths.rad(-20),Maths.rad(30),5F);
        progressRotationPrev(legfront_right,flyProgress,Maths.rad(35),Maths.rad(20),Maths.rad(-30),5F);
        progressRotationPrev(legmid_left,flyProgress,Maths.rad(35),Maths.rad(-35),Maths.rad(20),5F);
        progressRotationPrev(legmid_right,flyProgress,Maths.rad(35),Maths.rad(35),Maths.rad(-20),5F);
        progressRotationPrev(legback_left,flyProgress,Maths.rad(35),Maths.rad(-35),Maths.rad(20),5F);
        progressRotationPrev(legback_right,flyProgress,Maths.rad(35),Maths.rad(35),Maths.rad(-20),5F);
        progressRotationPrev(wing_left,walkProgress,Maths.rad(20),Maths.rad(-20),Maths.rad(20),5F);
        progressRotationPrev(wing_right,walkProgress,Maths.rad(20),Maths.rad(20),Maths.rad(-20),5F);
        progressRotationPrev(head,dragProgress,Maths.rad(-70),0,0,5F);
        progressRotationPrev(fang_right,dragProgress,0,0,Maths.rad(20),5F);
        progressRotationPrev(fang_left,dragProgress,0,0,Maths.rad(-20),5F);
        progressPositionPrev(head,dragProgress,0,3,-1,5F);
        progressPositionPrev(body,sitProgress,0,7,0,5F);
        progressRotationPrev(legfront_right,sitProgress,0,Maths.rad(-25),Maths.rad(27),5F);
        progressRotationPrev(legfront_left,sitProgress,0,Maths.rad(25),Maths.rad(-27),5F);
        progressRotationPrev(legmid_right,sitProgress,0,0,Maths.rad(21),5F);
        progressRotationPrev(legmid_left,sitProgress,0,0,Maths.rad(-21),5F);
        progressRotationPrev(legback_right,sitProgress,0,Maths.rad(25),Maths.rad(27),5F);
        progressRotationPrev(legback_left,sitProgress,0,Maths.rad(-25),Maths.rad(-27),5F);
        progressRotationPrev(head,sitProgress,Maths.rad(-20),0,0,5F);

        // Sting states
        progressRotationPrev(abdomen,stingGroundProgress,Maths.rad(-70),0,0,5F);
        progressRotationPrev(stinger,stingGroundProgress,Maths.rad(-30),0,0,5F);
        progressRotationPrev(body,stingGroundProgress,Maths.rad(-40),0,0,5F);
        progressPositionPrev(body,stingGroundProgress,0,-2,0,5F);
        progressPositionPrev(abdomen,stingGroundProgress,0,0,2,5F);
        progressRotationPrev(legfront_right,stingGroundProgress,Maths.rad(40),0,Maths.rad(-40),5F);
        progressRotationPrev(legfront_left,stingGroundProgress,Maths.rad(40),0,Maths.rad(40),5F);
        progressRotationPrev(legmid_right,stingGroundProgress,Maths.rad(40),0,Maths.rad(-10),5F);
        progressRotationPrev(legmid_left,stingGroundProgress,Maths.rad(40),0,Maths.rad(10),5F);
        progressRotationPrev(body,stingFlyProgress,Maths.rad(-70),0,0,5F);
        progressRotationPrev(abdomen,stingFlyProgress,Maths.rad(-50),0,0,5F);
        progressRotationPrev(stinger,stingFlyProgress,Maths.rad(-30),0,0,5F);
        progressPositionPrev(body,stingFlyProgress,0,-5,0,5F);
        progressPositionPrev(abdomen,stingFlyProgress,0,0,2,5F);
        progressPositionPrev(stinger,5F-stingProgress,0,0,-3,5F);

        // Dig state
        progressRotationPrev(body,digProgress,Maths.rad(40),0,0,5F);
        progressRotationPrev(head,digProgress,Maths.rad(-20),0,0,5F);
        progressRotationPrev(legfront_right,digProgress,Maths.rad(-50),0,Maths.rad(20),5F);
        progressRotationPrev(legfront_left,digProgress,Maths.rad(-50),0,Maths.rad(-20),5F);
        progressRotationPrev(legmid_right,digProgress,Maths.rad(-10),0,Maths.rad(-10),5F);
        progressRotationPrev(legmid_left,digProgress,Maths.rad(-10),0,Maths.rad(10),5F);
        progressRotationPrev(legback_left,digProgress,Maths.rad(-30),0,Maths.rad(30),5F);
        progressRotationPrev(legback_right,digProgress,Maths.rad(-30),0,Maths.rad(-30),5F);
        this.swing(legfront_left,digSpeed,digDegree*1,false,1,-0.5F,ageInTicks,digProgress*0.2F);
        this.swing(legfront_right,digSpeed,digDegree*1,false,1,0.5F,ageInTicks,digProgress*0.2F);
        this.swing(head,digSpeed,digDegree*1,false,0,0F,ageInTicks,digProgress*0.2F);

        float f=Maths.rad(flyAngle);
        this.body.rotateAngleZ+=f;

        if(dragProgress==0){this.faceTarget(netHeadYaw,headPitch,1.2F,head);}
    }

    @Override public void renderToBuffer(PoseStack ms,VertexConsumer b,int l,int o,float r,float g,float bl,float a){root.render(ms,b,l,o);}
    public void setRotationAngle(AdvancedModelBox a,float x,float y,float z){a.rotateAngleX=x;a.rotateAngleY=y;a.rotateAngleZ=z;}
}
