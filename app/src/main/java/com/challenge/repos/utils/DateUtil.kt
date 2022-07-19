package com.challenge.repos.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Mahmoud Hamwi on 17-Jul-22.
 */
class DateUtil {
    companion object {
        private fun parseDateStringToDateObject(StringDate: String?, useUTC: Boolean): Date? {
            if (StringDate == null || StringDate.isEmpty()) {
                return null
            }
            val simpleDateFormat = SimpleDateFormat(Constants.DATE_PATTERN_RECEIVED, Locale.ENGLISH)
            try {
                if (useUTC) {
                    simpleDateFormat.timeZone = TimeZone.getTimeZone("UTC")
                } else {
                    simpleDateFormat.timeZone = TimeZone.getDefault()
                }

                return simpleDateFormat.parse(StringDate)
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            return null
        }

        fun formatDate(StringDate: String?, useUTC: Boolean): String? {
            val date = parseDateStringToDateObject(StringDate, useUTC) ?: return StringDate
            val sdf = SimpleDateFormat(Constants.DATE_PATTERN_TO_DISPLAY, Locale.ENGLISH)
            return sdf.format(date)
        }
    }
}