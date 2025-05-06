package com.learn._06_optional;

import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Optional 实践指南
 */
public class OptionalMain {
    public static void main(String[] args) {
        getInfo(null, "");
    }

    public static String getInfo(String vehicleName, String vehicleModelCode) {
        // ------自己真实工作代码的 Optional 改造案例

        // String vehicleModel = null;
        // if (StringUtils.isNotBlank(vehicleName)) {
        //     List<String> vehicleModelList = queryVehicleModelByOldCarSeries(vehicleName);
        //     if (vehicleModelList != null && !vehicleModelList.isEmpty()){
        //         vehicleModel = vehicleModelList.get(0);
        //     }else {
        //         if (StringUtils.isNotBlank(vehicleModelCode)){
        //             vehicleModelList = queryVehicleModelByOldVehicleModel(vehicleModelCode);
        //             if (vehicleModelList != null && !vehicleModelList.isEmpty()){
        //                 vehicleModel = vehicleModelList.get(0);
        //             }
        //         }
        //     }
        // }
        // return vehicleModel;

        return Optional.ofNullable(vehicleName)
                .filter(StringUtils::isNotBlank)
                .map(name -> queryVehicleModelByOldCarSeries(name))
                .filter(list -> !list.isEmpty())
                .map(list -> list.get(0))
                .orElseGet(() -> Optional.ofNullable(vehicleModelCode)
                        .filter(StringUtils::isNotBlank)
                        .map(modelCode -> queryVehicleModelByOldVehicleModel(modelCode))
                        .filter(list -> !list.isEmpty())
                        .map(list -> list.get(0))
                        .orElse(null));
    }

    public static List<String> queryVehicleModelByOldCarSeries(String vehicleName) {
        return Collections.singletonList(vehicleName);
    }

    public static List<String> queryVehicleModelByOldVehicleModel(String vehicleModelCode) {
        return Collections.singletonList(vehicleModelCode);
    }


    public static String tmp(String infoStr) {
        // 1 值变量 -> Optional变量
        String var1 = "";
        Optional<String> optional = Optional.ofNullable(var1);

        // 2 Optional 变量 -> null 指定或转换返回 / 非 null 值变量原样返回
        // 原始代码----------------
        String var2 = null;
        if (var2 == null) {
            var2 = "";
        }
        // 优化代码----------------
        var2 = Optional.ofNullable(var2).orElse("");

        // 原始代码----------------
        String var3 = null;
        if (var3 == null) {
            var3 = defaultVarValue();
        }
        // 优化代码----------------
        var3 = Optional.ofNullable(var3).orElseGet(() -> defaultVarValue());

        // 原始代码----------------
        String var4 = null;
        String var5 = "";
        if (var4 != null){
            System.out.println(var4);
        }else {
            System.out.println(var5);
        }
        // 优化代码----------------
        String display = Optional.ofNullable(var4).orElse(var5);
        System.out.println(display);

        // 3 Optional 变量 -> 不为 null 就执行操作 filter/map/ifPresent, 为 null 不执行 -> Optional 变量

        return "";
    }

    public static String defaultVarValue() {
        return "";
    }
}
