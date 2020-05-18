package net.app315.hydra.example.common.utils;

import com.jgw.supercodeplatform.exception.SuperCodeException;
import com.jgw.supercodeplatform.exception.SuperCodeExtException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.http.HttpStatus;
import org.apache.http.util.Asserts;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author shixiongfei
 * @date 2020-03-02
 * @since
 */
public interface LocalDateTimeUtil {

    String YEAR_AND_MONTH = "yyyy-MM";

    String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    String DATE_PATTERN = "yyyy-MM-dd";

    String TIME_PATTERN = "HH:mm:ss";

    String SPLIT_STR = " ~ ";

    String SPLIT_STR_NO_SPACE = "~";

    String YEAR = "%s年";

    String YEAR_QUARTER = "%s年第%s季度";

    String YEAR_MONTH = "%s年%s月";

    /**
     * 将时间类型转换为字符串，转换格式自定义
     *
     * @param dateTime 时间
     * @param pattern  转换格式
     * @return
     */
    static String formatDateTime2Str(LocalDateTime dateTime, String pattern) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return dateTimeFormatter.format(dateTime);
    }

    /**
     * 将时间类型转换为年月日时分秒的字符串类型， 转换格式：yyyy-MM-dd HH:mm:ss
     * ps: 2018-01-01 12:12:12
     *
     * @param dateTime 时间
     * @return
     */
    static String formatDateTime2Str(LocalDateTime dateTime) {
        return formatDateTime2Str(dateTime, DATE_TIME_PATTERN);
    }

    /**
     * 将时间类型转换为年月日的字符串类型，转换格式为：yyyy-MM-dd
     * ps: 2018-01-01
     *
     * @param dateTime 时间
     * @return
     */
    static String formatDate2Str(LocalDateTime dateTime) {
        return formatDateTime2Str(dateTime, DATE_PATTERN);
    }

    /**
     * 将时间类型转换为时分秒的字符串格式，转换格式为：HH:mm:ss
     *
     * @param dateTime 时间
     * @return
     */
    static String formatTime2Str(LocalDateTime dateTime) {
        return formatDateTime2Str(dateTime, TIME_PATTERN);
    }


    /**
     * 将字符串类型转换为时间类型, 仅支持 {@code yyyy-MM-dd HH:mm:ss} 格式类型
     *
     * @param dateTime 时间字符串
     * @return
     */
    static LocalDateTime parseStr2LocalDateTime(String dateTime) throws SuperCodeException {
        Asserts.notEmpty(dateTime, "时间字符串不可为空");
        try {
            // yyyy-MM-dd HH:mm:ss => 长度为19
            if (dateTime.length() < 19) {
                Date date = DateUtils.parseDate(dateTime, DATE_PATTERN);
                return date2LocalDateTime(date);
            }
            return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(DATE_TIME_PATTERN));
        } catch (ParseException e) {
            e.printStackTrace();
            throw new SuperCodeException("时间转换失败", HttpStatus.SC_INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 将 {@link Date} 转换为 {@link LocalDateTime} 类型，时区默认为东8区（北京时间）
     */
    static LocalDateTime date2LocalDateTime(Date date) {
        return date.toInstant().atOffset(ZoneOffset.of("+8")).toLocalDateTime();
    }


    /**
     * 将 {@link LocalDateTime} 转换为 {@link Date} 类型，时区默认为东8区（北京时间）
     */
    static Date localDateTime2Date(LocalDateTime localDateTime) {
        return Date.from(localDateTime.toInstant(ZoneOffset.of("+8")));
    }

    /**
     * 对时间区间进行分割
     *
     * @return
     */
    static String[] substringDate(String dateInterval) {
        if (StringUtils.isNotBlank(dateInterval)) {
            return dateInterval.trim().split(SPLIT_STR);
        }
        return new String[2];
    }

    /**
     * 获取一段时间的每一天的年月日集合
     *
     * @param start 开始时间
     * @param end   结束时间
     * @return
     */
    static List<String> getDateInterval(String start, String end) {
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);

        if (startDate.isEqual(endDate)) {
            ArrayList<String> result = new ArrayList<>(1);
            result.add(startDate.toString());
            return result;
        }

        // 获取天数区间
        long distance = getDayInterval(startDate, endDate);
        int capacity = (int) (distance + 1);
        // 获取时间区间，是为了创建ArrayList集合时，直接指定容量大小，
        // 防止出现天数间距过大， 造成ArrayList集合进行多次扩容，影响性能
        List<String> list = new ArrayList<>(capacity);

        Stream.iterate(startDate, d -> d.plusDays(1)).limit(distance + 1).forEach(f -> list.add(f.toString()));

        return list;
    }

    /**
     * 获取年月日
     *
     * @return
     */
    static String getYYD(Date dateTime) {
        if (Objects.isNull(dateTime)) {
            throw new SuperCodeExtException("时间不可为空");
        }

        return DateFormatUtils.format(dateTime, "yyyy-MM-dd");
    }

    /**
     * 计算日期{@code startDate}与{@code endDate}的间隔天数
     *
     * @return
     */
    static long getDayInterval(LocalDate startDate, LocalDate endDate) {
        return ChronoUnit.DAYS.between(startDate, endDate);
    }

    /**
     * 对日期进行加指定天操作
     *
     * @param
     * @return
     * @author shixiongfei
     * @date 2019-09-23
     * @updateDate 2019-09-23
     * @updatedBy shixiongfei
     */
    static String localDatePlusDays(String dateStr, int days) {
        if (StringUtils.isNotBlank(dateStr)) {
            LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(DATE_PATTERN));
            dateStr = date.plusDays(days).toString();
        }
        return dateStr;
    }

    /**
     * 对日期进行加指定天操作
     *
     * @param
     * @return
     * @author shixiongfei
     * @date 2019-09-23
     * @updateDate 2019-09-23
     * @updatedBy shixiongfei
     */
    static String addOneDay(String dateStr) {
        return localDatePlusDays(dateStr, 1);
    }

    /**
     * 获取开始和结束时间
     *
     * @author shixiongfei
     * @date 2019-10-21
     * @updateDate 2019-10-21
     * @updatedBy shixiongfei
     * @param count 查询的表的条数，没有则默认从2019-01-01开始
     * @return
     */
    static List<LocalDate> getStartAndEndDate(int count) {
        List<LocalDate> list = new ArrayList<>(2);
        // 数据为空时，进行历史数据同步
        list.add(count == 0 ? LocalDate.of(2019, 1, 1) : LocalDate.now().minusDays(1));
        list.add( LocalDate.now());
        return list;
    }

    /**
     * 获取当月的开始和结束时间
     *
     * @author shixiongfei
     * @date 2019-10-28
     * @updateDate 2019-10-28
     * @updatedBy shixiongfei
     * @param
     * @return
     */
    static List<String> getStartAndEndDate(String startDate, String endDate) {
        List<String> list = new ArrayList<>(2);
        list.add(LocalDate.parse(startDate).with(TemporalAdjusters.firstDayOfMonth()).toString());
        list.add(LocalDate.parse(endDate).with(TemporalAdjusters.lastDayOfMonth()).toString());
        return list;
    }

    /**
     * 通过开始和结束时间获取开始和结束的月集合
     *
     * @author shixiongfei
     * @date 2019-10-23
     * @updateDate 2019-10-23
     * @updatedBy shixiongfei
     * @param startDate 参数格式必须为 yyyy-MM-dd
     * @param endDate 参数格式必须为 yyyy-MM-dd
     * @return
     */
    static List<LocalDate> getStartAndEndMonth(String startDate, String endDate) {
        LocalDate start = LocalDate.parse(startDate).with(TemporalAdjusters.firstDayOfMonth());
        LocalDate end = LocalDate.parse(endDate).with(TemporalAdjusters.firstDayOfMonth());
        int between = (int) ChronoUnit.MONTHS.between(start, end);
        List<LocalDate> list = new ArrayList<>(between + 2);
        list.add(start);

        // 如果年月相同，则直接返回
        if (start.isEqual(end)) {
            return list;
        }

        if (between > 0) {
            for (int i = 1; i < between; i++) {
                list.add(start.plusMonths(i));
            }
        }
        list.add(end);
        return list;
    }

    /**
     * 获取最近半年的年月信息
     *
     * @author shixiongfei
     * @date 2019-12-18
     * @updateDate 2019-12-18
     * @updatedBy shixiongfei
     * @param
     * @return
     */
    static List<String> listHalfYearAndMonth() {
        LocalDate now = LocalDate.now();
        return IntStream.rangeClosed(-5, 0).mapToObj(i -> {
            LocalDate date = now.plusMonths(i);
            return date.format(DateTimeFormatter.ofPattern(YEAR_AND_MONTH));
        }).collect(Collectors.toList());
    }

    /**
     * 对开始结束时间进行相连
     *
     * @author shixiongfei
     * @date 2019-10-29
     * @updateDate 2019-10-29
     * @updatedBy shixiongfei
     * @param
     * @return
     */
    static String contactDates(String startQueryDate, String endQueryDate) {
        return startQueryDate + SPLIT_STR_NO_SPACE + endQueryDate;
    }

    /**
     * 获取年度字符串 ps:2019年
     *
     * @author shixiongfei
     * @date 2019-11-05
     * @updateDate 2019-11-05
     * @updatedBy shixiongfei
     * @param
     * @return
     */
    static String getYearStr(Date queryDate) {
        LocalDate localDate = queryDate.toInstant().atZone(ZoneOffset.ofHours(8)).toLocalDate();
        return String.format(YEAR, localDate.getYear());
    }

    /**
     * 获取年度 ps:2019
     *
     * @author shixiongfei
     * @date 2019-11-05
     * @updateDate 2019-11-05
     * @updatedBy shixiongfei
     * @param
     * @return
     */
    static Integer getYear(Date queryDate) {
        LocalDate localDate = queryDate.toInstant().atZone(ZoneOffset.ofHours(8)).toLocalDate();
        return localDate.getYear();
    }

    /**
     * 获取指定时间所在的季度字符串， ps: 第1季度
     *
     * @author shixiongfei
     * @date 2019-11-05
     * @updateDate 2019-11-05
     * @updatedBy shixiongfei
     * @param
     * @return
     */
    static String getQuarterStr(Date queryDate) {
        LocalDate localDate = queryDate.toInstant().atZone(ZoneOffset.ofHours(8)).toLocalDate();
        return localDate.query((temporal) -> {
            LocalDate now = LocalDate.from(temporal);

            if (now.isBefore(now.with(Month.APRIL).withDayOfMonth(1))) {
                return QuarterEnum.FIRST.getValue();
            } else if (now.isBefore(now.with(Month.JULY).withDayOfMonth(1))) {
                return QuarterEnum.SECOND.getValue();
            } else if (now.isBefore(now.with(Month.OCTOBER).withDayOfMonth(1))) {
                return QuarterEnum.THIRD.getValue();
            } else {
                return QuarterEnum.FOURTH.getValue();
            }
        });
    }

    /**
     * 获取指定时间所在的季度， ps: 1
     *
     * @author shixiongfei
     * @date 2019-11-05
     * @updateDate 2019-11-05
     * @updatedBy shixiongfei
     * @param
     * @return
     */
    static Integer getQuarter(Date queryDate) {
        LocalDate localDate = queryDate.toInstant().atZone(ZoneOffset.ofHours(8)).toLocalDate();
        return localDate.query((temporal) -> {
            LocalDate now = LocalDate.from(temporal);

            if (now.isBefore(now.with(Month.APRIL).withDayOfMonth(1))) {
                return QuarterEnum.FIRST.getKey();
            } else if (now.isBefore(now.with(Month.JULY).withDayOfMonth(1))) {
                return QuarterEnum.SECOND.getKey();
            } else if (now.isBefore(now.with(Month.OCTOBER).withDayOfMonth(1))) {
                return QuarterEnum.THIRD.getKey();
            } else {
                return QuarterEnum.FOURTH.getKey();
            }
        });
    }

    /**
     * 获取年度 + 季度 字符串 ps:2019年第1季度
     *
     * @author shixiongfei
     * @date 2019-11-05
     * @updateDate 2019-11-05
     * @updatedBy shixiongfei
     * @param
     * @return
     */
    static String getYearAndQuarterStr(Date queryDate) {
        LocalDate localDate = queryDate.toInstant().atZone(ZoneOffset.ofHours(8)).toLocalDate();
        return localDate.query((temporal) -> {
            LocalDate now = LocalDate.from(temporal);
            int year = now.getYear();

            if (now.isBefore(now.with(Month.APRIL).withDayOfMonth(1))) {
                return String.format(YEAR_QUARTER, year, QuarterEnum.FIRST.getKey());
            } else if (now.isBefore(now.with(Month.JULY).withDayOfMonth(1))) {
                return String.format(YEAR_QUARTER, year, QuarterEnum.SECOND.getKey());
            } else if (now.isBefore(now.with(Month.OCTOBER).withDayOfMonth(1))) {
                return String.format(YEAR_QUARTER, year, QuarterEnum.THIRD.getKey());
            } else {
                return String.format(YEAR_QUARTER, year, QuarterEnum.FOURTH.getKey());
            }
        });
    }

    /**
     * 获取年度 + 月度字符串 ps: 2019年2月
     *
     * @author shixiongfei
     * @date 2019-11-05
     * @updateDate 2019-11-05
     * @updatedBy shixiongfei
     * @param
     * @return
     */
    static String getYearAndMonth(Date queryDate) {
        LocalDate localDate = queryDate.toInstant().atZone(ZoneOffset.ofHours(8)).toLocalDate();
        return String.format(YEAR_MONTH, localDate.getYear(), localDate.getMonthValue());
    }
}

/**
 * 季度枚举
 *
 * @author shixiongfei
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
enum QuarterEnum {

    /**
     * 第1季度
     */
    FIRST(1, "第1季度"),

    /**
     * 第2季度
     */
    SECOND(2, "第2季度"),

    /**
     * 第3季度
     */
    THIRD(3, "第3季度"),

    /**
     * 第4季度
     */
    FOURTH(4, "第4季度");

    private int key;

    private String value;
}