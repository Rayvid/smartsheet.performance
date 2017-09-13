package smartsheet.performance

import com.smartsheet.api.SmartsheetBuilder
import java.time.Duration;
import java.time.LocalDateTime;

fun main(args : Array<String>) {
    val smartSheetToken = "4fvr17lry9y2unj177s1ijb2kk" // TODO this should come from some kind of config like ENV variable

    val startTime = LocalDateTime.now()

    val smartSheet = SmartsheetBuilder().setAccessToken(smartSheetToken).build()
    val sheets = smartSheet.sheetResources().listSheets(null, null, null)
    val sheetId = sheets.data[0].id // Default to first sheet

    // TODO: Uncomment if you wish to read a specific sheet
    // sheetId = 239236234L;

    // Load entire sheet
    val sheet = smartSheet.sheetResources().getSheet(sheetId, null, null, null, null, null, null, null)

    var rows = sheet.rows;
    for (rowNo in 0 .. (rows.size - 1)) {
        val row = rows[rowNo]
        println(row.getColumnByIndex(4))
    }

    println("Elapsed time ${Duration.between(startTime, LocalDateTime.now()).toMillis()} ms")
}