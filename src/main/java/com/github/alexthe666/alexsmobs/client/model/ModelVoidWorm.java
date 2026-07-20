package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntityVoidWorm;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.util.Mth;

public class ModelVoidWorm extends AdvancedEntityModel<EntityVoidWorm> {
	private final AdvancedModelBox root,neck,topfrills_left,topfrills_right,bottomfrills_left,bottomfrills_right,head,eye_bottom_r1,eye_top_r1,topjaw,bottomjaw;

	public ModelVoidWorm(float f){texWidth=256;texHeight=256;
		root=new AdvancedModelBox(this,"root");root.setPos(0.0F,24.0F,0.0F);
		neck=new AdvancedModelBox(this,"neck");neck.setPos(0.0F,-10.0F,20.0F);root.addChild(neck);neck.setTextureOffset(0,53).addBox(-10.0F,-10.0F,-28.0F,20.0F,20.0F,28.0F,f,false);
		topfrills_left=new AdvancedModelBox(this,"topfrills_left");topfrills_left.setPos(10.0F,-10.0F,-20.0F);neck.addChild(topfrills_left);setRotationAngle(topfrills_left,0.0F,0.0F,0.7854F);topfrills_left.setTextureOffset(71,76).addBox(0.0F,-9.0F,-7.0F,0.0F,9.0F,26.0F,f,false);
		topfrills_right=new AdvancedModelBox(this,"topfrills_right");topfrills_right.setPos(-10.0F,-10.0F,-20.0F);neck.addChild(topfrills_right);setRotationAngle(topfrills_right,0.0F,0.0F,-0.7854F);topfrills_right.setTextureOffset(71,76).addBox(0.0F,-9.0F,-7.0F,0.0F,9.0F,26.0F,f,true);
		bottomfrills_left=new AdvancedModelBox(this,"bottomfrills_left");bottomfrills_left.setPos(10.0F,10.0F,-20.0F);neck.addChild(bottomfrills_left);setRotationAngle(bottomfrills_left,0.0F,0.0F,2.3562F);bottomfrills_left.setTextureOffset(71,76).addBox(0.0F,-9.0F,-7.0F,0.0F,9.0F,26.0F,f,false);
		bottomfrills_right=new AdvancedModelBox(this,"bottomfrills_right");bottomfrills_right.setPos(-10.0F,10.0F,-20.0F);neck.addChild(bottomfrills_right);setRotationAngle(bottomfrills_right,0.0F,0.0F,-2.3562F);bottomfrills_right.setTextureOffset(71,76).addBox(0.0F,-9.0F,-7.0F,0.0F,9.0F,26.0F,f,true);
		head=new AdvancedModelBox(this,"head");head.setPos(0.0F,0.0F,-28.0F);neck.addChild(head);head.setTextureOffset(0,0).addBox(-17.0F,-17.0F,-18.0F,34.0F,34.0F,18.0F,f,false);head.setTextureOffset(25,102).addBox(17.0F,-5.0F,-14.0F,2.0F,10.0F,10.0F,f,false);head.setTextureOffset(0,102).addBox(-19.0F,-5.0F,-14.0F,2.0F,10.0F,10.0F,f,false);
		eye_bottom_r1=new AdvancedModelBox(this,"eye_bottom_r1");eye_bottom_r1.setPos(0.0F,18.0F,-9.0F);head.addChild(eye_bottom_r1);setRotationAngle(eye_bottom_r1,0.0F,0.0F,1.5708F);eye_bottom_r1.setTextureOffset(0,53).addBox(-1.0F,-5.0F,-5.0F,2.0F,10.0F,10.0F,f,false);
		eye_top_r1=new AdvancedModelBox(this,"eye_top_r1");eye_top_r1.setPos(0.0F,-18.0F,-9.0F);head.addChild(eye_top_r1);setRotationAngle(eye_top_r1,0.0F,0.0F,1.5708F);eye_top_r1.setTextureOffset(69,54).addBox(-1.0F,-5.0F,-5.0F,2.0F,10.0F,10.0F,f,false);
		topjaw=new AdvancedModelBox(this,"topjaw");topjaw.setPos(0.0F,3.0F,-18.0F);head.addChild(topjaw);topjaw.setTextureOffset(98,64).addBox(-5.0F,-10.0F,-16.0F,10.0F,10.0F,16.0F,f,false);
		bottomjaw=new AdvancedModelBox(this,"bottomjaw");bottomjaw.setPos(0.0F,-3.0F,-17.9F);head.addChild(bottomjaw);bottomjaw.setTextureOffset(89,37).addBox(-5.0F,0.0F,-16.0F,10.0F,10.0F,16.0F,f-0.1F,false);
		this.updateDefaultPose();}

	@Override public Iterable<BasicModelPart> parts(){return ImmutableList.of(root);}
	@Override public Iterable<AdvancedModelBox> getAllParts(){return ImmutableList.of(root,neck,head,bottomfrills_left,bottomfrills_right,eye_bottom_r1,eye_top_r1,topfrills_left,topfrills_right,topjaw,bottomjaw);}

	@Override
	public void setupAnim(EntityVoidWorm entityIn,float limbSwing,float limbSwingAmount,float ageInTicks,float netHeadYaw,float headPitch){
		this.resetToDefaultPose();
		//══════ 🪱 VOID WORM — ABYSSAL TUNNELER WITH FRILLED MAW ══════
		// IDENTITY: Burrows through dimensional void. Head is a giant
		// beak-like maw surrounded by 4 frills that ripple with alien
		// geometry. Neck pulses with peristaltic wave. Eyes glow.
		// UNIQUE vs Anaconda (snake): has frilled maw + vertical jaws.
		// BIOMECHANICS: The frills are sensory organs that ripple to
		// detect dimensional anomalies. Jaws open vertically (dorsal/ventral)
		// for biting through space itself.

		this.root.rotateAngleX+=headPitch*Mth.DEG_TO_RAD;
		this.root.rotationPointY+=-3-Mth.clamp(headPitch*-0.125F,-10,10)*0.3F;
		this.root.rotationPointZ+=-15+(limbSwingAmount*10);

		float pt=ageInTicks-entityIn.tickCount;
		float yawAmount=(entityIn.prevWormAngle+(entityIn.getWormAngle()-entityIn.prevWormAngle)*pt)/57.295776F*0.5F;
		float jawProgress=entityIn.prevJawProgress+(entityIn.jawProgress-entityIn.prevJawProgress)*pt;

		//══════ HEAD TRACKING + SCAN ══════
		neck.rotateAngleZ+=yawAmount;
		// Head slowly scans side to side while tunneling
		head.rotateAngleY+=Mth.sin(ageInTicks*0.15F)*0.08F;

		//══════ ALL 4 FRILLS: alien ripple ══════
		// Previously only top frills were animated. Now all 4 pulse.
		float frillWave1=Mth.sin(ageInTicks*0.12F)*0.06F;
		float frillWave2=Mth.sin(ageInTicks*0.15F+1F)*0.04F;
		this.flap(topfrills_left,0.12F,0.05F,false,0F,0.02F,ageInTicks,1);
		this.flap(topfrills_right,0.12F,0.05F,true,0.5F,0.02F,ageInTicks,1);
		this.flap(bottomfrills_left,0.11F,0.04F,false,1F,0.01F,ageInTicks,1);
		this.flap(bottomfrills_right,0.11F,0.04F,true,1.5F,0.01F,ageInTicks,1);
		// Frills also sway with neck movement
		topfrills_left.rotateAngleZ+=frillWave1;
		topfrills_right.rotateAngleZ-=frillWave1;
		bottomfrills_left.rotateAngleZ+=frillWave2;
		bottomfrills_right.rotateAngleZ-=frillWave2;

		//══════ NECK: peristaltic ripple ══════
		// Neck pulses as if swallowing dimensional energy
		float neckPulse=Mth.sin(ageInTicks*0.08F);
		neck.setScale(1.0F+neckPulse*0.03F,1.0F+neckPulse*0.02F,1.0F+neckPulse*0.03F);
		neck.rotationPointY+=neckPulse*0.12F;

		//══════ JAWS: vertical bite ══════
		progressRotationPrev(bottomjaw,jawProgress,Maths.rad(60),0,0,5F);
		progressRotationPrev(topjaw,jawProgress,Maths.rad(-60),0,0,5F);
		progressPositionPrev(bottomjaw,jawProgress,0,2,-5,5F);
		progressPositionPrev(topjaw,jawProgress,0,-2,-5,5F);
		// Jaw micro-clatter at idle
		bottomjaw.rotateAngleX+=neckPulse*0.02F;
		topjaw.rotateAngleX-=neckPulse*0.02F;

		//══════ EYES: glowing pulse ══════
		float eyePulse=1.0F+neckPulse*0.04F;
		eye_top_r1.setScale(eyePulse,eyePulse,eyePulse);
		eye_bottom_r1.setScale(eyePulse,eyePulse,eyePulse);

		//══════ BREATHING: dimensional respiration ══════
		float breath=Mth.cos(ageInTicks*0.05F);
		neck.rotationPointY+=breath*0.15F;
	}

	public void setRotationAngle(AdvancedModelBox a,float x,float y,float z){a.rotateAngleX=x;a.rotateAngleY=y;a.rotateAngleZ=z;}
}
