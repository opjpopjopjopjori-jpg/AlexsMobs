package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntityMimicube;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;

public class ModelMimicube extends AdvancedEntityModel<EntityMimicube> {
	public final AdvancedModelBox root;
	public final AdvancedModelBox body;
	public final AdvancedModelBox innerbody;
	public final AdvancedModelBox mouth;
	public final AdvancedModelBox eye_left;
	public final AdvancedModelBox eye_right;

	public ModelMimicube() {
		texWidth = 64;
		texHeight = 64;

		root = new AdvancedModelBox(this, "root");
		root.setPos(0.0F, 24.0F, 0.0F);
		

		body = new AdvancedModelBox(this, "body");
		body.setPos(0.0F, 0.0F, 0.0F);
		root.addChild(body);
		body.setTextureOffset(0, 0).addBox(-8.0F, -14.0F, -8.0F, 16.0F, 14.0F, 16.0F, 0.0F, false);

		innerbody = new AdvancedModelBox(this, "innerbody");
		innerbody.setPos(0.0F, -7.0F, 0.0F);
		root.addChild(innerbody);
		innerbody.setTextureOffset(0, 31).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);

		mouth = new AdvancedModelBox(this, "mouth");
		mouth.setPos(2.0F, 4.0F, -5.0F);
		innerbody.addChild(mouth);
		mouth.setTextureOffset(0, 12).addBox(-2.0F, -2.0F, 0.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);

		eye_left = new AdvancedModelBox(this, "eye_left");
		eye_left.setPos(3.5F, -1.5F, -4.0F);
		innerbody.addChild(eye_left);
		setRotationAngle(eye_left, 0.0F, 0.0F, 0.3054F);
		eye_left.setTextureOffset(0, 6).addBox(-1.5F, -1.5F, -1.0F, 3.0F, 3.0F, 2.0F, 0.0F, false);

		eye_right = new AdvancedModelBox(this, "eye_right");
		eye_right.setPos(-3.5F, -0.5F, -4.0F);
		innerbody.addChild(eye_right);
		setRotationAngle(eye_right, 0.0F, 0.0F, -0.3927F);
		eye_right.setTextureOffset(0, 0).addBox(-1.5F, -1.5F, -1.0F, 3.0F, 3.0F, 2.0F, 0.0F, false);
		this.updateDefaultPose();
	}

	@Override
	public Iterable<BasicModelPart> parts() {
		return ImmutableList.of(root);
	}

	@Override
	public Iterable<AdvancedModelBox> getAllParts() {
		return ImmutableList.of(root, body, innerbody, eye_left, eye_right, mouth);
	}

	@Override
	public void setupAnim(EntityMimicube entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		this.resetToDefaultPose();
        //══════ 🟨 MIMICUBE — GELATINOUS SHAPE-SHIFTER ══════
        // IDENTITY: Gelatinous cube. Micro-jiggle wobble. Mouth pulses.
        // Eyes wander independently. Body squishes and bounces.
        // UNIQUE vs Blobfish (fish blob), BananaSlug (mollusk).
		//═══ AAA BIOMECHANICS: GELATINOUS CUBE-LIKE ═══
		// Mimicubes are semi-solid shapeshifters — constant micro-jiggle,
		// breathing through surface undulation, eyes track independently
		float lvt_6_1_ = Mth.lerp(Minecraft.getInstance().getFrameTime(), entity.prevSquishFactor, entity.squishFactor);
		float lvt_7_1_ = 1.0F / (lvt_6_1_ + 1.0F);
		float squishScale = 1.0F / lvt_7_1_;

		// ── BREATHING: outer body slow pulse, inner body faster jiggle ──
		float breath = Mth.cos(ageInTicks * 0.1F);
		body.setScale(1.0F + breath * 0.02F, squishScale + breath * 0.015F, 1.0F + breath * 0.02F);
		innerbody.rotationPointY += breath * 0.1F;

		// ── MICRO-JIGGLE: gelatinous creatures never stop wobbling ──
		this.bob(innerbody, 0.15F, 0.4F, false, ageInTicks, 1);
		this.flap(innerbody, 0.12F, 0.08F, false, 2F, 0, ageInTicks, 1);
		this.swing(innerbody, 0.08F, 0.04F, false, 3F, 0, ageInTicks, 1);

		// ── SQUISH MECHANIC (preserved) ──
		this.innerbody.rotationPointY += lvt_6_1_ * -5F;

		// ── MOUTH PULSE ──
		mouth.setScale(1.0F + breath * 0.1F, 1.0F + breath * 0.08F, 1.0F);

		// ── EYE TRACKING ──
		this.eye_left.rotateAngleY += netHeadYaw * Mth.DEG_TO_RAD * 0.5F;
		this.eye_left.rotateAngleX += headPitch * Mth.DEG_TO_RAD * 0.4F;
		this.eye_right.rotateAngleY += netHeadYaw * Mth.DEG_TO_RAD * 0.5F;
		this.eye_right.rotateAngleX += headPitch * Mth.DEG_TO_RAD * 0.4F;

		// ── EYEBALL WANDER (independent micro-stare) ──
		eye_left.rotationPointX += Mth.sin(ageInTicks * 0.3F + 1F) * 0.08F;
		eye_left.rotationPointY += Mth.cos(ageInTicks * 0.35F) * 0.06F;
		eye_right.rotationPointX += Mth.sin(ageInTicks * 0.3F + 2F) * 0.08F;
		eye_right.rotationPointY += Mth.cos(ageInTicks * 0.35F + 1F) * 0.06F;

		// ── BODY BOUNCE (always present, scale with movement) ──
		float bounce = Mth.abs(Mth.sin(limbSwing * 0.6F)) * limbSwingAmount * 0.4F;
		body.rotationPointY += bounce;
		innerbody.rotationPointY += bounce * 0.6F;
	}


	public void setRotationAngle(AdvancedModelBox AdvancedModelBox, float x, float y, float z) {
		AdvancedModelBox.rotateAngleX = x;
		AdvancedModelBox.rotateAngleY = y;
		AdvancedModelBox.rotateAngleZ = z;
	}
}