import com.ms.taskprac.model.response.GetCryptoRateListResp

fun currencyConvert(amount: Double, currencyData: GetCryptoRateListResp.Data): Double {
    val rate = currencyData.rateUsd?.toDoubleOrNull() ?: return 0.0 // Handle potential parsing errors
    return amount / rate
}