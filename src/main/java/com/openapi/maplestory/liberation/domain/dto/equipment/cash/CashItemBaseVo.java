package com.openapi.maplestory.liberation.domain.dto.equipment.cash;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CashItemBaseVo {
    @JsonProperty("cash_item_option")
    private List<CashItemOptionVo> cashItemOptionVo = new ArrayList<>();
    private String date_option_expire;
}
