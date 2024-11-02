package com.mrahmed.quiztime.Domain

import android.os.Parcel
import android.os.Parcelable

data class QuestionModel(
    val id: Int,
    val question: String?,
    val correctAnswer: String?,
    val options1: String?,
    val options2: String?,
    val options3: String?,
    val options4: String?,
    val score: Int,
    val picPath: String?,
    val clickedAnswer: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),

        ) {

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(question)
        parcel.writeString(correctAnswer)
        parcel.writeString(options1)
        parcel.writeString(options2)
        parcel.writeString(options3)
        parcel.writeString(options4)
        parcel.writeInt(score)
        parcel.writeString(picPath)
        parcel.writeString(clickedAnswer)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<QuestionModel> {
        override fun createFromParcel(parcel: Parcel): QuestionModel {
            return QuestionModel(parcel)
        }

        override fun newArray(size: Int): Array<out QuestionModel?>? {
            return arrayOfNulls(size)
        }
    }


}
