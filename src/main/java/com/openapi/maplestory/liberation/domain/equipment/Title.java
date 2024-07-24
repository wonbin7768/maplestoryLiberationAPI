package com.openapi.maplestory.liberation.domain.equipment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Title {
    private String title_name;
    private String title_description;
    private String date_option_expire;
}
