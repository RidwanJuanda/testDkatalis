package com.example.testdkatalis.response.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsData(
    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("created_at")
    val createdAt: String? = null,

    @SerializedName("updated_at")
    val updatedAt: String,

    @SerializedName("slug_url")
    val slugUrl: String? = null,

    @SerializedName("author_id")
    val authorId: Int? = null,

    @SerializedName("title")
    val title: String? = null,

    @SerializedName("source_image")
    val sourceImage: String? = null,

    @SerializedName("images")
    val images: List<String>? = null,

    @SerializedName("description")
    val description: String? = null,

    @SerializedName("content")
    val content: String? = null,

    @SerializedName("tags")
    val tags: String? = null,

    @SerializedName("persons")
    val persons: String,

    @SerializedName("status")
    val status: String? = null,

    @SerializedName("total_views")
    val totalViews: Int? = null,

    @SerializedName("url_youtube")
    val urlYoutube: String? = null,

    @SerializedName("category_id")
    val categoryId: Int? = null,

    @SerializedName("type_thumbnail")
    val typeThumbnail: String?= null,

    @SerializedName("keywords")
    val keywords: String? = null,

    @SerializedName("magazine_category")
    val magazineCategory: MagazineCategory? = null,

    @SerializedName("author")
    val author: Author? = null,

    @SerializedName("content_type")
    val contentType: Int?= null,

    @SerializedName("introduction")
    val introduction: String? = null,

    @SerializedName("name_event")
    val nameEvent: String?= null,

    @SerializedName("start_date_event")
    val startDateEvent: String? = null, //yyyy-MM-dd'T'hh:mm:ss'+'07:00

    @SerializedName("end_date_event")
    val endDateEvent: String?= null, //yyyy-MM-dd'T'hh:mm:ss'+'07:00

    @SerializedName("location")
    val location: String? = null,

    @SerializedName("image_venue")
    val imageVenue: List<String>?= null,

    @SerializedName("artis_name")
    val artis_name: String? = null,

    @SerializedName("spotify_link")
    val spotifyLink: String?= null,

    @SerializedName("schedule_time")
    val schedule_time: String? = null, //hh:mm:ss

    @SerializedName("special_content")
    val special_content: String? = null
) : Parcelable

@Parcelize
data class MagazineCategory(
    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("name")
    val name: String? = null
) : Parcelable

@Parcelize
data class Author(
    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("avatar_url")
    val avatarUrl : String? = null
):Parcelable

@Parcelize
data class Meta(
    @SerializedName("current_page")
    val currentPage: Int? = null,

    @SerializedName("per_page")
    val perPage: Int? = null,

    @SerializedName("total")
    val total: Int? = null,

    @SerializedName("total_page")
    val totalPage: Int? = null
):Parcelable