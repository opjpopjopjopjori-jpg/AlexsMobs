package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntityGeladaMonkey;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.ModelAnimator;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.util.Mth;

public class ModelGeladaMonkey extends AdvancedEntityModel<EntityGeladaMonkey> {
	private final AdvancedModelBox root;
	private final AdvancedModelBox body;
	private final AdvancedModelBox tail;
	private final AdvancedModelBox torso;
	private final AdvancedModelBox neck;
	private final AdvancedModelBox head;
	private final AdvancedModelBox mouth;
	private final AdvancedModelBox left_arm;
	private final AdvancedModelBox right_arm;
	private final AdvancedModelBox left_leg;
	private final AdvancedModelBox right_leg;
	public final ModelAnimator animator;

	public ModelGeladaMonkey() {
		texWidth = 128;
		texHeight = 128;

		root = new AdvancedModelBox(this, "root");
		root.setRotationPoint(0.0F, 24.0F, 0.0F);
		body = new AdvancedModelBox(this, "body");
		body.setRotationPoint(0.0F, -11.0F, 4.0F);
		root.addChild(body);
		body.setTextureOffset(30, 36).addBox(-3.5F, -3.0F, -5.0F, 7.0F, 7.0F, 9.0F, 0.0F, false);

		tail = new AdvancedModelBox(this, "tail");
		tail.setRotationPoint(0.0F, -1.5F, 3.0F);
		body.addChild(tail);
		setRotationAngle(tail, 0.5672F, 0.0F, 0.0F);
		tail.setTextureOffset(0, 0).addBox(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 20.0F, 0.0F, false);

		torso = new AdvancedModelBox(this, "torso");
		torso.setRotationPoint(0.0F, 0.0F, -4.0F);
		body.addChild(torso);
		torso.setTextureOffset(0, 24).addBox(-4.5F, -5.0F, -9.0F, 9.0F, 10.0F, 10.0F, 0.0F, false);
		torso.setTextureOffset(27, 0).addBox(-4.5F, 5.0F, -9.0F, 9.0F, 3.0F, 10.0F, 0.0F, false);

		neck = new AdvancedModelBox(this, "neck");
		neck.setRotationPoint(0.0F, -0.9F, -8.6F);
		torso.addChild(neck);
		neck.setTextureOffset(39, 24).addBox(-5.0F, -5.0F, -3.0F, 10.0F, 7.0F, 4.0F, 0.0F, false);
		neck.setTextureOffset(50, 67).addBox(-8.0F, -5.0F, -2.0F, 16.0F, 11.0F, 0.0F, 0.0F, false);
		neck.setTextureOffset(25, 60).addBox(-4.0F, -5.0F, -2.4F, 8.0F, 7.0F, 3.0F, 0.0F, false);

		head = new AdvancedModelBox(this, "head");
		head.setRotationPoint(0.0F, -1.0F, -2.0F);
		neck.addChild(head);
		head.setTextureOffset(0, 0).addBox(-2.5F, -2.0F, -2.0F, 5.0F, 4.0F, 3.0F, 0.0F, false);

		mouth = new AdvancedModelBox(this, "mouth");
		mouth.setRotationPoint(0.0F, 1.0F, -1.0F);
		head.addChild(mouth);
		setRotationAngle(mouth, -1.0908F, 0.0F, 0.0F);
		mouth.setTextureOffset(0, 8).addBox(-1.5F, -1.5F, -1.2F, 3.0F, 6.0F, 4.0F, 0.0F, false);

		left_arm = new AdvancedModelBox(this, "left_arm");
		left_arm.setRotationPoint(2.0F, 3.0F, -7.0F);
		torso.addChild(left_arm);
		left_arm.setTextureOffset(11, 45).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);

		right_arm = new AdvancedModelBox(this, "right_arm");
		right_arm.setRotationPoint(-2.0F, 3.0F, -7.0F);
		torso.addChild(right_arm);
		right_arm.setTextureOffset(11, 45).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, true);

		left_leg = new AdvancedModelBox(this, "left_leg");
		left_leg.setRotationPoint(2.3F, 5.0F, 2.0F);
		body.addChild(left_leg);
		left_leg.setTextureOffset(0, 45).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 7.0F, 3.0F, 0.0F, false);

		right_leg = new AdvancedModelBox(this, "right_leg");
		right_leg.setRotationPoint(-2.3F, 5.0F, 2.0F);
		body.addChild(right_leg);
		right_leg.setTextureOffset(0, 45).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 7.0F, 3.0F, 0.0F, true);
		this.updateDefaultPose();
		animator = ModelAnimator.create();
	}

	public void animate(IAnimatedEntity entity, float f, float f1, float f2, float f3, float f4) {
		this.resetToDefaultPose();
		animator.update(entity);
		animator.setAnimation(EntityGeladaMonkey.ANIMATION_SWIPE_L);
		animator.startKeyframe(5);
		animator.rotate(body, 0, Maths.rad(5F), 0);
		animator.rotate(head, 0, Maths.rad(-5F), 0);
		animator.rotate(left_arm, Maths.rad(25F), Maths.rad(10F), 0);
		animator.endKeyframe();
		animator.startKeyframe(3);
		animator.rotate(body, Maths.rad(-15F), 0, 0);
		animator.rotate(head, Maths.rad(5F), 0, 0);
		animator.rotate(right_arm, Maths.rad(15F), 0, 0);
		animator.rotate(right_leg, Maths.rad(15F), 0, 0);
		animator.rotate(left_leg, Maths.rad(15F), 0, 0);
		animator.rotate(left_arm, Maths.rad(-80F),  Maths.rad(-20F),0);
		animator.endKeyframe();
		animator.resetKeyframe(5);
		animator.setAnimation(EntityGeladaMonkey.ANIMATION_SWIPE_R);
		animator.startKeyframe(5);
		animator.rotate(body, 0, Maths.rad(-5F), 0);
		animator.rotate(head, 0, Maths.rad(5F), 0);
		animator.rotate(right_arm, Maths.rad(25F), Maths.rad(-10F), 0);
		animator.endKeyframe();
		animator.startKeyframe(3);
		animator.rotate(body, Maths.rad(-15F), 0, 0);
		animator.rotate(head, Maths.rad(5F), 0, 0);
		animator.rotate(left_arm, Maths.rad(15F), 0, 0);
		animator.rotate(right_leg, Maths.rad(15F), 0, 0);
		animator.rotate(left_leg, Maths.rad(15F), 0, 0);
		animator.rotate(right_arm, Maths.rad(-80F),  Maths.rad(20F),0);
		animator.endKeyframe();
		animator.resetKeyframe(5);
		animator.setAnimation(EntityGeladaMonkey.ANIMATION_CHEST);
		animator.startKeyframe(5);
		standPose();
		animator.rotate(right_arm, 0, 0,  Maths.rad(25F));
		animator.rotate(left_arm, 0, 0,  Maths.rad(-25F));
		animator.endKeyframe();
		animator.startKeyframe(5);
		standPose();
		animator.rotate(neck, 0, 0,  Maths.rad(25F));
		animator.rotate(right_arm, 0, 0,  Maths.rad(25F));
		animator.rotate(left_arm, 0, 0,  Maths.rad(-25F));
		animator.endKeyframe();
		animator.startKeyframe(10);
		standPose();
		animator.rotate(neck, 0, 0,  Maths.rad(-25F));
		animator.rotate(right_arm, 0, 0,  Maths.rad(5F));
		animator.rotate(left_arm, 0, 0,  Maths.rad(-5F));
		animator.endKeyframe();
		animator.startKeyframe(10);
		standPose();
		animator.rotate(neck, 0, 0,  Maths.rad(25F));
		animator.rotate(right_arm, 0, 0,  Maths.rad(25F));
		animator.rotate(left_arm, 0, 0,  Maths.rad(-25F));
		animator.endKeyframe();
		animator.resetKeyframe(5);
		animator.setAnimation(EntityGeladaMonkey.ANIMATION_GROOM);
		animator.startKeyframe(3);
		animator.rotate(left_arm, Maths.rad(-10F), 0, 0);
		animator.rotate(right_arm, Maths.rad(-90F),  Maths.rad(-10F),0);
		animator.endKeyframe();
		animator.startKeyframe(5);
		animator.rotate(neck, Maths.rad(20F), 0, Maths.rad(20F));
		animator.rotate(left_arm, Maths.rad(-10F), 0, 0);
		animator.rotate(right_arm, Maths.rad(-90F),  Maths.rad(-10F),0);
		animator.move(neck, 1, 0, -1);
		animator.move(right_arm, 0, 0, 3);
		animator.endKeyframe();
		animator.startKeyframe(5);
		animator.rotate(neck, Maths.rad(5F), 0, Maths.rad(10F));
		animator.rotate(left_arm, Maths.rad(-10F), 0, 0);
		animator.rotate(right_arm, Maths.rad(-130F),  Maths.rad(-10F),0);
		animator.move(neck, 1, 0, -1);
		animator.move(right_arm, 0, 0, 2);
		animator.endKeyframe();
		animator.startKeyframe(4);
		animator.endKeyframe();
		animator.startKeyframe(3);
		animator.rotate(right_arm, Maths.rad(-10F), 0, 0);
		animator.rotate(left_arm, Maths.rad(-90F),  Maths.rad(10F),0);
		animator.endKeyframe();
		animator.startKeyframe(5);
		animator.rotate(neck, Maths.rad(20F), 0, Maths.rad(-20F));
		animator.rotate(right_arm, Maths.rad(-10F), 0, 0);
		animator.rotate(left_arm, Maths.rad(-90F),  Maths.rad(10F),0);
		animator.move(neck, -1, 0, -1);
		animator.move(right_arm, 0, 0, 3);
		animator.endKeyframe();
		animator.startKeyframe(5);
		animator.rotate(neck, Maths.rad(5F), 0, Maths.rad(-10F));
		animator.rotate(right_arm, Maths.rad(-10F), 0, 0);
		animator.rotate(left_arm, Maths.rad(-130F),  Maths.rad(10F),0);
		animator.move(neck, -1, 0, -1);
		animator.move(right_arm, 0, 0, 2);
		animator.endKeyframe();
		animator.resetKeyframe(5);
	}

	private void standPose(){
		animator.rotate(body,  Maths.rad(-65F), 0, 0);
		animator.rotate(neck,  Maths.rad(65F), 0, 0);
		animator.rotate(right_leg,  Maths.rad(65F), 0, 0);
		animator.rotate(left_leg,  Maths.rad(65F), 0, 0);
		animator.rotate(right_arm,  Maths.rad(65F), 0, 0);
		animator.rotate(left_arm,  Maths.rad(65F), 0, 0);
		animator.rotate(tail,  Maths.rad(50F), 0, 0);
		animator.move(body, 0, 0.5F, -2);
		animator.move(neck, 0, -1, -2);
		animator.move(right_leg, 0, -1, 1);
		animator.move(left_leg, 0, -1, 1);
		animator.move(right_arm, -1, 1, -1);
		animator.move(left_arm, 1, 1, -1);
	}

	@Override
	public void setupAnim(EntityGeladaMonkey entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		animate(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		boolean running=entity.isAggro();
		float runSpeed=0.7F,runDegree=0.7F,walkSpeed=0.65F,walkDegree=0.45F,idleSpeed=0.15F,idleDegree=0.5F;
		float stillProgress=(1F-limbSwingAmount)*5F;
		float sitProgress=entity.prevSitProgress+(entity.sitProgress-entity.prevSitProgress)*(ageInTicks-entity.tickCount);

		//══════ 🐵 GELADA MONKEY — GROUND-FORAGING GRASS PLUCKER ══════
		// IDENTITY: The ONLY grass-grazing primate. Sits on bottom shuffling while
		// plucking grass with both hands. Chest displays red patch. Bipedal shuffle.
		// Tail drags passively behind (NOT prehensile like capuchin).
		// UNIQUE vs Capuchin (arboreal acrobat) vs Gorilla (knuckle-walker).

		// ── BREATHING: grazing herbivore rhythm ──
		float breath=Mth.cos(ageInTicks*0.1F);
		torso.setScale(1.0F,1.0F+breath*0.02F,1.0F);
		torso.rotationPointY+=breath*0.15F;
		mouth.rotationPointY+=breath*0.02F;
		body.rotationPointY+=breath*0.08F;

		// ── GROUND-FORAGING POSTURE: gelada naturally leans forward, bottom on ground ──
		// Arms reach forward for grass; this is gelada's natural feeding posture
		if(limbSwingAmount<0.05F&&sitProgress<0.1F){
			torso.rotateAngleX-=0.12F; // lean forward slightly (foraging)
			neck.rotateAngleX-=0.08F; // head down toward grass
			head.rotateAngleX-=0.06F;
		}

		// ── CHEST DISPLAY: gelada's signature — red chest patch ──
		// Males puff chest occasionally; chest area pulses
		torso.setScale(1.0F+breath*0.03F,1.0F+breath*0.02F,1.0F);

		// ── TAIL: drags passively behind (not active like capuchin) ──
		progressRotationPrev(tail,stillProgress,Maths.rad(-40),0,0,5F);
		this.swing(tail,idleSpeed,idleDegree,true,0,0F,ageInTicks,1);
		// Tail sways lazily during walk
		this.walk(tail,walkSpeed,-walkDegree*0.2F,true,1F,0.1F,limbSwing,limbSwingAmount);
		this.flap(tail,walkSpeed,walkDegree*0.2F,false,2F,0F,limbSwing,limbSwingAmount);

		// ── BIPEDAL SHUFFLE: gelada's unique locomotion ──
		// Geladas shuffle on their bottoms or walk bipedally short distances
		// Unlike gorilla (knuckle-walk) or capuchin (quadrupedal scamper)
		this.bob(neck,idleSpeed*0.5F,idleDegree*0.25F,false,ageInTicks,1);

		if(running){
			// ── BIPEDAL RUN: upright, legs pumping, arms swinging ──
			this.walk(body,runSpeed,runDegree*0.2F,true,0F,0F,limbSwing,limbSwingAmount);
			this.walk(tail,runSpeed,runDegree*0.5F,true,0F,0.3F,limbSwing,limbSwingAmount);
			this.walk(neck,runSpeed,runDegree*0.2F,true,1F,0F,limbSwing,limbSwingAmount);
			// Arms pump for balance (human-like running)
			this.walk(right_arm,runSpeed,runDegree*1.2F,true,0F,0F,limbSwing,limbSwingAmount);
			this.walk(left_arm,runSpeed,runDegree*1.2F,true,0F,0F,limbSwing,limbSwingAmount);
			// Legs drive forward
			this.walk(right_leg,runSpeed,runDegree*1.2F,false,0,0F,limbSwing,limbSwingAmount);
			this.walk(left_leg,runSpeed,runDegree*1.2F,false,0,0F,limbSwing,limbSwingAmount);
			this.flap(right_leg,runSpeed,runDegree*0.2F,true,0,-0.2F,limbSwing,limbSwingAmount);
			this.flap(left_leg,runSpeed,runDegree*0.2F,false,0,-0.2F,limbSwing,limbSwingAmount);
			this.bob(body,runSpeed,runDegree*3F,false,limbSwing,limbSwingAmount);
		}else{
			// ── BOTTOM-SHUFFLE WALK: gelada's unique quad-to-bipedal transition ──
			// Sits on bottom, shuffles forward while plucking grass
			this.walk(body,walkSpeed,walkDegree*0.1F,true,0F,0F,limbSwing,limbSwingAmount);
			this.walk(neck,walkSpeed,-walkDegree*0.1F,true,1F,0F,limbSwing,limbSwingAmount);
			this.walk(right_arm,walkSpeed,walkDegree*1.0F,true,0F,0F,limbSwing,limbSwingAmount);
			this.walk(left_arm,walkSpeed,walkDegree*1.0F,false,0F,0F,limbSwing,limbSwingAmount);
			// Hands reach forward to pluck grass (alternating grab)
			this.flap(right_arm,walkSpeed*0.5F,walkDegree*0.3F,false,1F,0,limbSwing,limbSwingAmount);
			this.flap(left_arm,walkSpeed*0.5F,walkDegree*0.3F,true,1F,0,limbSwing,limbSwingAmount);
			this.walk(right_leg,walkSpeed,walkDegree*1.0F,false,0F,0F,limbSwing,limbSwingAmount);
			this.walk(left_leg,walkSpeed,walkDegree*1.0F,true,0F,0F,limbSwing,limbSwingAmount);
			this.bob(right_leg,walkSpeed,walkDegree*-1.2F,true,limbSwing,limbSwingAmount);
			this.bob(left_leg,walkSpeed,walkDegree*-1.2F,true,limbSwing,limbSwingAmount);
		}

		// ── SIT TRANSITION ──
		progressRotationPrev(body,sitProgress,Maths.rad(-40),0,0,5F);
		progressRotationPrev(left_leg,sitProgress,Maths.rad(-45),Maths.rad(-20),0,5F);
		progressRotationPrev(right_leg,sitProgress,Maths.rad(-45),Maths.rad(20),0,5F);
		progressRotationPrev(left_arm,sitProgress,Maths.rad(40),0,0,5F);
		progressRotationPrev(right_arm,sitProgress,Maths.rad(40),0,0,5F);
		progressRotationPrev(tail,sitProgress,Maths.rad(50),0,0,5F);
		progressRotationPrev(neck,sitProgress,Maths.rad(40),0,0,5F);
		progressPositionPrev(body,sitProgress,0,5F,0,5F);
		progressPositionPrev(left_leg,sitProgress,0,-2.5F,0,5F);
		progressPositionPrev(right_leg,sitProgress,0,-2.5F,0,5F);
		progressPositionPrev(left_arm,sitProgress,0,2,2,5F);
		progressPositionPrev(right_arm,sitProgress,0,2,2,5F);

		neck.rotateAngleY+=netHeadYaw/57.295776F*0.5F;
		neck.rotateAngleX+=headPitch/57.295776F;

		if(entity.isBaby()){
			head.setScale(1.3F,1.3F,1.3F);neck.setScale(1.25F,1.25F,1.25F);
		}else{
			neck.setScale(1.0F,1.0F,1.0F);head.setScale(1.0F,1.0F,1.0F);
		}
	}

	@Override
	public Iterable<BasicModelPart> parts(){return ImmutableList.of(root);}

	@Override
	public Iterable<AdvancedModelBox> getAllParts(){
		return ImmutableList.of(root,body,tail,torso,neck,head,left_arm,left_leg,right_arm,right_leg,mouth);
	}

	public void setRotationAngle(AdvancedModelBox AdvancedModelBox, float x, float y, float z){
		AdvancedModelBox.rotateAngleX=x;AdvancedModelBox.rotateAngleY=y;AdvancedModelBox.rotateAngleZ=z;
	}
}
