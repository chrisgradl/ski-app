package at.cgradl.skiapp.model

data class PersonRanking(
    val Rank: Int,
    val PersonId: Int,
    val FirstName: String,
    val LastName: String,
    val PersonImage: String,
    val GenderId: Int,
    val NationCC3: String,
    val NationImage: String,
    val Value: Int
): java.io.Serializable