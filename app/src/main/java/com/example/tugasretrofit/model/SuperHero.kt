package com.example.tugasretrofit.model

import com.google.gson.annotations.SerializedName

data class SuperHeroModel(
	@SerializedName("result")
	val `results`: List<SuperHeroData>
)

data class SuperHeroData(

	@SerializedName("image")
	val `image`: String,

	@SerializedName("id")
	val `id`: Int,

	@SerializedName("title")
	val `title`: String
)
