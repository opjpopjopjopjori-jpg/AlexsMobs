package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntityHammerheadShark;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.util.Mth;

public class ModelHammerheadShark extends AdvancedEntityModel<EntityHammerheadShark> {
    private final AdvancedModelBox root,main_body,head,head_hammer,topfin,topfintail,tail1,tail2,tail3,tailbottomend,tailtopend,finL,finR,tail_finL,tail_finR;

    public ModelHammerheadShark(){texWidth=128;texHeight=128;
        root=new AdvancedModelBox(this,"root");root.setPos(0.0F,24.0F,0.0F);
        main_body=new AdvancedModelBox(this,"main_body");main_body.setPos(0.0F,-10.0F,0.0F);root.addChild(main_body);main_body.setTextureOffset(0,0).addBox(-5.0F,-8.0F,-14.0F,10.0F,12.0F,25.0F,0.0F,false);
        head=new AdvancedModelBox(this,"head");head.setPos(0.0F,-1.0F,-16.0F);main_body.addChild(head);head.setTextureOffset(46,0).addBox(-3.0F,-4.0F,-5.0F,6.0F,6.0F,7.0F,0.0F,false);
        head_hammer=new AdvancedModelBox(this,"head_hammer");head_hammer.setPos(0.0F,-4.0F,-3.0F);head.addChild(head_hammer);head_hammer.setTextureOffset(0,38).addBox(-10.0F,-1.0F,-3.0F,20.0F,2.0F,5.0F,0.0F,false);
        topfin=new AdvancedModelBox(this,"topfin");topfin.setPos(0.0F,-7.0F,-4.0F);main_body.addChild(topfin);topfin.setTextureOffset(72,0).addBox(-1.0F,-8.0F,0.0F,2.0F,9.0F,12.0F,0.0F,false);
        topfintail=new AdvancedModelBox(this,"topfintail");topfintail.setPos(0.0F,0.0F,7.0F);main_body.addChild(topfintail);topfintail.setTextureOffset(53,20).addBox(-1.0F,-4.0F,0.0F,2.0F,5.0F,9.0F,0.0F,false);
        tail1=new AdvancedModelBox(this,"tail1");tail1.setPos(0.0F,-1.0F,11.0F);main_body.addChild(tail1);tail1.setTextureOffset(76,22).addBox(-3.0F,-4.0F,0.0F,6.0F,7.0F,10.0F,0.0F,false);
        tail2=new AdvancedModelBox(this,"tail2");tail2.setPos(0.0F,0.0F,10.0F);tail1.addChild(tail2);tail2.setTextureOffset(37,44).addBox(-2.0F,-3.0F,0.0F,4.0F,5.0F,8.0F,0.0F,false);
        tail3=new AdvancedModelBox(this,"tail3");tail3.setPos(0.0F,0.0F,8.0F);tail2.addChild(tail3);tail3.setTextureOffset(0,46).addBox(-1.0F,-2.0F,0.0F,2.0F,3.0F,6.0F,0.0F,false);
        tailbottomend=new AdvancedModelBox(this,"tailbottomend");tailbottomend.setPos(0.0F,1.0F,6.0F);tail3.addChild(tailbottomend);tailbottomend.setTextureOffset(0,0).addBox(-1.0F,0.0F,-3.0F,2.0F,1.0F,5.0F,0.0F,false);
        tailtopend=new AdvancedModelBox(this,"tailtopend");tailtopend.setPos(0.0F,-1.0F,6.0F);tail3.addChild(tailtopend);tailtopend.setTextureOffset(0,0).addBox(-1.0F,-1.0F,-3.0F,2.0F,1.0F,7.0F,0.0F,false);
        finL=new AdvancedModelBox(this,"finL");finL.setPos(5.0F,0.0F,-2.0F);main_body.addChild(finL);finL.setTextureOffset(100,0).addBox(0.0F,0.0F,-3.0F,8.0F,1.0F,7.0F,0.0F,false);
        finR=new AdvancedModelBox(this,"finR");finR.setPos(-5.0F,0.0F,-2.0F);main_body.addChild(finR);finR.setTextureOffset(100,0).addBox(-8.0F,0.0F,-3.0F,8.0F,1.0F,7.0F,0.0F,true);
        tail_finL=new AdvancedModelBox(this,"tail_finL");tail_finL.setPos(0.0F,0.0F,-2.0F);tail3.addChild(tail_finL);tail_finL.setTextureOffset(0,56).addBox(0.0F,0.0F,-4.0F,0.0F,3.0F,8.0F,0.0F,false);
        tail_finR=new AdvancedModelBox(this,"tail_finR");tail_finR.setPos(0.0F,0.0F,-2.0F);tail3.addChild(tail_finR);tail_finR.setTextureOffset(0,56).addBox(0.0F,0.0F,-4.0F,0.0F,3.0F,8.0F,0.0F,true);
        this.updateDefaultPose();}

    @Override public Iterable<BasicModelPart> parts(){return ImmutableList.of(root);}
    @Override public Iterable<AdvancedModelBox> getAllParts(){return ImmutableList.of(root,main_body,head,head_hammer,topfin,topfintail,tail1,tail2,tail3,tailbottomend,tailtopend,finL,finR,tail_finL,tail_finR);}

    @Override
    public void setupAnim(EntityHammerheadShark entity,float limbSwing,float limbSwingAmount,float ageInTicks,float netHeadYaw,float headPitch){
        this.resetToDefaultPose();
        float swimSpeed=0.2F,swimDegree=0.4F;

        //══════ 🔨 HAMMERHEAD SHARK — ELECTRORECEPTIVE OCEAN PREDATOR ══════
        // IDENTITY: Wide hammer-shaped head (cephalofoil) packed with
        // ampullae of Lorenzini — electroreceptors that detect prey buried
        // in sand. Head sweeps like metal detector. THUNNIFORM swimming:
        // tail drives propulsion, body stays rigid. Dorsal fin iconic.
        // UNIQUE: Only shark with true hammer-head scanning behavior.
        // BIOMECHANICS: Sharks use thunniform locomotion — only the tail
        // and caudal peduncle oscillate. The body stays rigid unlike eels.
        // Pectoral fins are stabilizers, not rowers.

        //══════ BREATHING: ram ventilation ══════
        float breath=Mth.cos(ageInTicks*0.04F);
        main_body.setScale(1.0F+breath*0.01F,1.0F+breath*0.02F,1.0F+breath*0.01F);
        main_body.rotationPointY+=breath*0.25F;

        //══════ HAMMER HEAD: electroreceptive scanning ══════
        float scanSlow=Mth.sin(ageInTicks*0.12F)*0.3F*(1-limbSwingAmount*0.5F);
        float scanFast=Mth.sin(ageInTicks*0.4F+1F)*0.1F*(1-limbSwingAmount*0.6F);
        head_hammer.rotateAngleY+=scanSlow+scanFast;
        head.rotateAngleY+=scanSlow*0.6F;
        head_hammer.rotateAngleZ+=Mth.sin(ageInTicks*0.1F+0.5F)*0.05F;

        //══════ THUNNIFORM SWIMMING ══════
        AdvancedModelBox[] tailChain={tail1,tail2,tail3};
        this.chainWave(tailChain,swimSpeed,swimDegree,-1.5F,limbSwing,limbSwingAmount);
        this.bob(main_body,swimSpeed,swimDegree*5F,false,limbSwing,limbSwingAmount);
        // Body recoils laterally from tail thrust (thunniform physics)
        main_body.rotationPointX+=Mth.sin(limbSwing*swimSpeed+1.5F)*swimDegree*0.4F*limbSwingAmount;

        //══════ TAIL FIN: heterocercal ══════
        tailtopend.rotateAngleY+=Mth.sin(limbSwing*swimSpeed+0.3F)*swimDegree*0.06F*limbSwingAmount;
        tailbottomend.rotateAngleY-=Mth.sin(limbSwing*swimSpeed+0.3F)*swimDegree*0.05F*limbSwingAmount;
        tail_finL.rotateAngleY+=Mth.sin(limbSwing*swimSpeed)*swimDegree*0.04F*limbSwingAmount;
        tail_finR.rotateAngleY-=Mth.sin(limbSwing*swimSpeed)*swimDegree*0.04F*limbSwingAmount;

        //══════ DORSAL FINS: iconic silhouette with passive sway ══════
        topfin.rotateAngleX+=Mth.sin(limbSwing*swimSpeed)*swimDegree*0.3F*limbSwingAmount;
        topfin.rotateAngleZ+=Mth.sin(ageInTicks*0.07F)*0.04F; // passive current sway
        topfintail.rotateAngleX+=Mth.sin(limbSwing*swimSpeed+0.5F)*swimDegree*0.15F*limbSwingAmount;
        topfintail.rotateAngleZ-=Mth.sin(ageInTicks*0.09F)*0.03F; // independent sway

        //══════ PECTORAL FINS: airplane-wing stabilizers ══════
        this.flap(finL,swimSpeed*0.5F,swimDegree*0.5F,true,3F,0,limbSwing,limbSwingAmount);
        this.flap(finR,swimSpeed*0.5F,swimDegree*0.5F,false,3F,0,limbSwing,limbSwingAmount);
        finL.rotateAngleX+=Mth.sin(limbSwing*swimSpeed+0.5F)*swimDegree*0.25F*limbSwingAmount;
        finR.rotateAngleX+=Mth.sin(limbSwing*swimSpeed+0.5F)*swimDegree*0.25F*limbSwingAmount;
        finL.rotateAngleZ-=Mth.sin(limbSwing*swimSpeed+1F)*swimDegree*0.12F*limbSwingAmount;
        finR.rotateAngleZ+=Mth.sin(limbSwing*swimSpeed+1F)*swimDegree*0.12F*limbSwingAmount;
        finL.rotateAngleZ+=Mth.sin(ageInTicks*0.05F)*0.06F;
        finR.rotateAngleZ-=Mth.sin(ageInTicks*0.05F+1F)*0.06F;

        this.main_body.rotateAngleY+=netHeadYaw*Mth.DEG_TO_RAD;
        this.main_body.rotateAngleX+=headPitch*Mth.DEG_TO_RAD;
    }
}
