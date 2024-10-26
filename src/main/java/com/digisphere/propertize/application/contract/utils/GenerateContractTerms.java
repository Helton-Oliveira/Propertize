package com.digisphere.propertize.application.contract.utils;

import java.time.LocalDate;

public class GenerateContractTerms {

    public static String generate(String address, Integer period, LocalDate startDate, LocalDate endDate, Double rentValue, Integer paymentDay, Double securityDeposit) {
        return String.format("""
            Contrato de Locação de Imóvel
        
            Este contrato de locação é celebrado entre o LOCADOR e o LOCATÁRIO, com a finalidade de estabelecer os termos e condições da locação do imóvel situado em %s.
        
            Cláusula 1: Objeto do Contrato
            O LOCADOR concede ao LOCATÁRIO, que aceita, o uso e gozo do imóvel descrito, em conformidade com as disposições deste contrato.
        
            Cláusula 2: Prazo de Locação
            O prazo de locação será de %d anos, com início em %s e término em %s, podendo ser renovado conforme a vontade das partes.
        
            Cláusula 3: Valor do Aluguel
            O LOCATÁRIO se compromete a pagar ao LOCADOR o valor mensal de R$ %.2f, até o dia %d de cada mês, através de boleto bancário.
        
            Cláusula 4: Garantias
            O LOCATÁRIO fornecerá ao LOCADOR uma garantia no valor de R$ %.2f, que será devolvida ao final da locação, descontadas eventuais despesas com danos ao imóvel.
        
            Cláusula 5: Rescisão
            O contrato poderá ser rescindido por qualquer uma das partes mediante aviso prévio de %d dias, com a devida justificação.
        
            Cláusula 6: Disposições Gerais
            Este contrato poderá ser alterado mediante acordo mútuo entre as partes, formalizado por escrito.
            """, address, period, startDate.toString(), endDate.toString(), rentValue, paymentDay, securityDeposit, 30);
    }
}
