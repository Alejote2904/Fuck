/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bike.model.custom;

import com.bike.model.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author desaextremo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountClient {
    private Long total;
    private Client client;
}
