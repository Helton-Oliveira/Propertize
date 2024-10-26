package com.digisphere.propertize.application.contract.utils;

public class GenerateMaintenanceClause {

    public static String generate() {
        return """
            Cláusula de Manutenção do Imóvel
        
            O LOCATÁRIO se compromete a zelar pela manutenção do imóvel, obrigando-se a realizar os seguintes procedimentos:
        
            Cláusula 1: Manutenção Preventiva
            O LOCATÁRIO deverá realizar a manutenção preventiva do imóvel, incluindo, mas não se limitando a: limpeza, verificação de instalações elétricas e hidráulicas, e conservação de áreas comuns.
        
            Cláusula 2: Reparos Emergenciais
            Em caso de reparos emergenciais que comprometam a segurança ou a habitabilidade do imóvel, o LOCATÁRIO deverá comunicar imediatamente o LOCADOR, que providenciará as medidas necessárias.
        
            Cláusula 3: Danos ao Imóvel
            O LOCATÁRIO será responsável por quaisquer danos causados ao imóvel durante o período de locação, exceto aqueles decorrentes do desgaste natural do uso. O LOCATÁRIO deverá reparar ou compensar o LOCADOR por danos verificados ao final do contrato.
        
            Cláusula 4: Inspeções
            O LOCADOR se reserva o direito de realizar inspeções periódicas no imóvel, mediante aviso prévio ao LOCATÁRIO, a fim de verificar as condições de manutenção e conservação.
        
            Cláusula 5: Alterações no Imóvel
            Qualquer alteração no imóvel, incluindo pinturas, reformas ou modificações, deverá ser previamente autorizada pelo LOCADOR, por escrito.
        
            Esses termos visam garantir uma relação harmoniosa e respeitosa entre as partes, promovendo a conservação do imóvel e a proteção dos direitos de ambos.
            """;
    }
}
