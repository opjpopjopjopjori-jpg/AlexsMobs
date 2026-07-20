package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntityFrilledShark;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.ModelAnimator;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.util.Mth;

public class ModelFrilledShark extends AdvancedEntityModel<EntityFrilledShark> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox body, head, jaw;
    private final AdvancedModelBox pectoralfin_left, pectoralfin_right;
    private final AdvancedModelBox tail1, tail2;
    private final AdvancedModelBox pelvicfin_left, pelvicfin_right;
    private final ModelAnimator animator;

    public ModelFrilledShark() {
        texWidth=128;texHeight=128;
        root=new AdvancedModelBox(this,"root");root.setPos(0,24,0);
        body=new AdvancedModelBox(this,"body");body.setPos(0,-3,0);root.addChild(body);
        body.setTextureOffset(0,0).addBox(-3,-3,-15,6,6,18,0,false);
        body.setTextureOffset(66,59).addBox(0,-9,-14,0,6,17,0,false);
        head=new AdvancedModelBox(this,"head");head.setPos(0,-2,-15);body.addChild(head);
        head.setTextureOffset(31,0).addBox(-3,-1,-7,6,3,7,0,false);
        jaw=new AdvancedModelBox(this,"jaw");jaw.setPos(0,2.4F,0.4F);head.addChild(jaw);
        setRotationAngle(jaw,0.2618F,0,0);jaw.setTextureOffset(41,25).addBox(-2.5F,0,-7,5,2,7,0,false);
        pectoralfin_left=new AdvancedModelBox(this,"pfl");pectoralfin_left.setPos(3,2.4F,-10);body.addChild(pectoralfin_left);
        setRotationAngle(pectoralfin_left,0,0,0.48F);pectoralfin_left.setTextureOffset(41,42).addBox(0,0,0,5,0,7,0,false);
        pectoralfin_right=new AdvancedModelBox(this,"pfr");pectoralfin_right.setPos(-3,2.4F,-10);body.addChild(pectoralfin_right);
        setRotationAngle(pectoralfin_right,0,0,-0.48F);pectoralfin_right.setTextureOffset(41,42).addBox(-5,0,0,5,0,7,0,true);
        tail1=new AdvancedModelBox(this,"t1");tail1.setPos(0,-0.9F,3);body.addChild(tail1);
        tail1.setTextureOffset(21,25).addBox(-2,-2,0,4,5,11,0,false);
        tail1.setTextureOffset(0,25).addBox(0,-5,5,0,3,6,0,false);
        pelvicfin_left=new AdvancedModelBox(this,"pl");pelvicfin_left.setPos(2,3,5);tail1.addChild(pelvicfin_left);
        setRotationAngle(pelvicfin_left,0,0,-0.9599F);pelvicfin_left.setTextureOffset(21,25).addBox(0,0,-1,0,3,5,0,false);
        pelvicfin_right=new AdvancedModelBox(this,"pr");pelvicfin_right.setPos(-2,3,5);tail1.addChild(pelvicfin_right);
        setRotationAngle(pelvicfin_right,0,0,0.9599F);pelvicfin_right.setTextureOffset(21,25).addBox(0,0,-1,0,3,5,0,true);
        tail2=new AdvancedModelBox(this,"t2");tail2.setPos(0,0.1F,11);tail1.addChild(tail2);
        tail2.setTextureOffset(0,25).addBox(0,-6,0,0,11,20,0,false);
        this.updateDefaultPose();animator=ModelAnimator.create();
    }

    public void animate(IAnimatedEntity entity,float f,float f1,float f2,float f3,float f4){
        animator.update(entity);
        animator.setAnimation(EntityFrilledShark.ANIMATION_ATTACK);
        animator.startKeyframe(5);animator.rotate(jaw,Maths.rad(-20),0,0);animator.move(head,0,0.5F,3);animator.endKeyframe();
        animator.startKeyframe(5);animator.rotate(head,Maths.rad(-10),0,0);animator.rotate(jaw,Maths.rad(40),0,0);animator.endKeyframe();
        animator.startKeyframe(5);animator.rotate(head,Maths.rad(5),0,0);animator.rotate(jaw,Maths.rad(-20),0,0);animator.endKeyframe();
        animator.resetKeyframe(2);
    }

    @Override public void setupAnim(EntityFrilledShark e,float limbSwing,float limbSwingAmount,float ageInTicks,float netHeadYaw,float headPitch){
        this.resetToDefaultPose();animate(e,limbSwing,limbSwingAmount,ageInTicks,netHeadYaw,headPitch);
        //══════ 🦈 FRILLED SHARK — LIVING FOSSIL EEL-SWIMMER ══════
        // IDENTITY: Ancient 80-million-year-old species. EEL-LIKE undulation
        // through deep water. Frilled gills ripple. Primitive jaw articulation.
        // UNIQUE vs other sharks: serpentine, not thunniform.
        AdvancedModelBox[] tailBoxes={head,body,tail1,tail2};
        float idSp=0.14F,idDg=0.25F,swSp=0.8F,swDg=0.75F;
        float land=e.prevOnLandProgress+(e.onLandProgress-e.prevOnLandProgress)*(ageInTicks-e.tickCount);

        // AAA SHARK BREATHING — water pumping over gills
        float breath=Mth.cos(ageInTicks*0.1F);
        body.setScale(1.0F+breath*0.02F,1.0F,1.0F);
        head.rotationPointY+=breath*0.06F;

        progressRotationPrev(body,land,0,0,Maths.rad(-100),5F);
        progressRotationPrev(pectoralfin_right,land,0,0,Maths.rad(-50),5F);
        progressRotationPrev(pectoralfin_left,land,0,0,Maths.rad(50),5F);
        this.walk(this.jaw,idSp,idDg,true,1F,-0.1F,ageInTicks,1);
        // Idle tail gentle sway
        this.swing(tail1,idSp*0.5F,idDg*0.2F,false,0,0,ageInTicks,1-limbSwingAmount*0.6F);
        this.swing(tail2,idSp*0.5F,idDg*0.3F,false,-1,0,ageInTicks,1-limbSwingAmount*0.6F);

        if(land>=5F){
            this.chainWave(tailBoxes,idSp,idDg*0.9F,-3,ageInTicks,1);
            this.flap(this.pectoralfin_right,idSp,idDg*2F,true,3,0.3F,ageInTicks,1);
            this.flap(this.pectoralfin_left,idSp,idDg*-2F,true,3,0.1F,ageInTicks,1);
        }else{
            this.chainWave(tailBoxes,swSp,swDg*0.9F,-3,limbSwing,limbSwingAmount);
            this.flap(this.pectoralfin_right,swSp,swDg,true,1F,0.3F,limbSwing,limbSwingAmount);
            this.flap(this.pectoralfin_left,swSp,swDg,true,1F,0.3F,limbSwing,limbSwingAmount);
            this.flap(this.pelvicfin_right,swSp,-swDg,true,3,0.1F,limbSwing,limbSwingAmount);
            this.flap(this.pelvicfin_left,swSp,-swDg,true,3,0.1F,limbSwing,limbSwingAmount);
            // AAA BODY BOB — vertical undulation during swim
            float bodyBob=Mth.sin(limbSwing*swSp*0.7F+1.5F)*swDg*0.25F*limbSwingAmount;
            body.rotationPointY+=bodyBob;tail1.rotationPointY-=bodyBob*0.4F;tail2.rotationPointY-=bodyBob*0.7F;
        }
    }

    @Override public Iterable<BasicModelPart> parts(){return ImmutableList.of(root);}
    @Override public Iterable<AdvancedModelBox> getAllParts(){return ImmutableList.of(root,body,head,tail1,tail2,jaw,pectoralfin_left,pectoralfin_right,pelvicfin_left,pelvicfin_right);}
    public void setRotationAngle(AdvancedModelBox a,float x,float y,float z){a.rotateAngleX=x;a.rotateAngleY=y;a.rotateAngleZ=z;}
}
