import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AppDatabase(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "apptismo.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "DataName"
        private const val COLUMN_NAME = "name"
    }

    private val _userNameFlow = MutableStateFlow<String>("Usuario")
    val userNameFlow: StateFlow<String> get() = _userNameFlow

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = "CREATE TABLE IF NOT EXISTS $TABLE_NAME ($COLUMN_NAME TEXT PRIMARY KEY)"
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertOrUpdateName(name: String) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NAME, name)
        }
        db.insertWithOnConflict(TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE)
        _userNameFlow.value = name // Emite el nuevo nombre a los observadores
    }

    fun getName(): String? {
        val db = readableDatabase
        val cursor = db.query(
            TABLE_NAME, arrayOf(COLUMN_NAME), null, null, null, null, null
        )
        return if (cursor.moveToFirst()) {
            cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME))
        } else {
            null
        }.also {
            cursor.close()
        }
    }
}
