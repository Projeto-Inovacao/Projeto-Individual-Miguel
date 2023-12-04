CREATE VIEW VW_RRAM_CHART_KT as 
select distinct 
 date_format(m1.data_hora,'%Y-%m-%d %H:%i:%s') AS data_hora,
 round(((1 - (m1.dado_coletado / m2.dado_coletado)) * 100),2) AS usado,
 round(((m1.dado_coletado / m2.dado_coletado) * 100),2) AS livre,
 m2.dado_coletado AS total,
 nocline.componente.nome_componente AS nome_componente,
 m.id_maquina AS id_maquina,m.hostname AS hostname,
 nocline.empresa.razao_social AS razao_social
 from ((((nocline.monitoramento m1 join nocline.monitoramento m2 on((m1.fk_maquina_monitoramento = m2.fk_maquina_monitoramento))) 
 join nocline.componente on((m1.fk_componentes_monitoramento = nocline.componente.id_componente))) 
 join nocline.maquina m on((m1.fk_maquina_monitoramento = m.id_maquina))) 
 join nocline.empresa on((m.fk_empresaM = nocline.empresa.id_empresa))) 
 where ((nocline.componente.nome_componente = 'RAM') and (m2.descricao = 'memoria total') and (m1.descricao = 'uso de ram kt'));

create view VW_JANELA_PROCESSO as
SELECT
    p.pid,
    p.nome_processo,
    v.data_hora,
    v.usado,
    TRUNCATE(p.uso_memoria, 2) as uso_memoria,
    p.status_abertura,
    v.id_maquina,
    p.fk_empresaP
FROM
    (
        SELECT
            DATE_FORMAT(m1.data_hora, '%Y-%m-%d %H:%i:%s') AS data_hora,
            ROUND(((1 - (m1.dado_coletado / m2.dado_coletado)) * 100), 2) AS usado,
            ROUND(((m1.dado_coletado / m2.dado_coletado) * 100), 2) AS livre,
            m2.dado_coletado AS total,
            componente.nome_componente,
            m.id_maquina,
            m.hostname,
            empresa.razao_social
        FROM
            nocline.monitoramento m1
            JOIN nocline.monitoramento m2 ON m1.fk_maquina_monitoramento = m2.fk_maquina_monitoramento
            JOIN nocline.componente ON m1.fk_componentes_monitoramento = componente.id_componente
            JOIN nocline.maquina m ON m1.fk_maquina_monitoramento = m.id_maquina
            JOIN nocline.empresa ON m.fk_empresaM = empresa.id_empresa
        WHERE
            componente.nome_componente = 'RAM'
            AND m2.descricao = 'memoria total'
            AND m1.descricao = 'uso de ram kt'
    ) v
INNER JOIN
    (
        SELECT
            pid,
            nome_processo,
            uso_cpu,
            uso_memoria,
            memoria_virtual,
            status_abertura,
            gravacao_disco,
            temp_execucao,
            fk_maquinaP,
            fk_empresaP,
            data_hora
        FROM
            processos
        WHERE
            data_hora IS NOT NULL
    ) p
ON
    v.data_hora = p.data_hora
ORDER BY
    v.data_hora, p.data_hora;

-- VIEW PRO SERVER


CREATE VIEW VW_RRAM_CHART_KT AS
SELECT DISTINCT
    FORMAT(m1.data_hora, 'yyyy-MM-dd HH:mm:ss') AS data_hora,
    ROUND(((1 - (m1.dado_coletado / m2.dado_coletado)) * 100), 2) AS usado,
    ROUND(((m1.dado_coletado / m2.dado_coletado) * 100), 2) AS livre,
    m2.dado_coletado AS total,
    c.nome_componente AS nome_componente,
    ma.id_maquina,
    ma.hostname,
    e.razao_social
FROM
    nocline.monitoramento m1
JOIN
    nocline.monitoramento m2 ON m1.fk_maquina_monitoramento = m2.fk_maquina_monitoramento
JOIN
    nocline.componente c ON m1.fk_componentes_monitoramento = c.id_componente
JOIN
    nocline.maquina ma ON m1.fk_maquina_monitoramento = ma.id_maquina
JOIN
    nocline.empresa e ON ma.fk_empresaM = e.id_empresa
WHERE
    c.nome_componente = 'RAM'
    AND m2.descricao = 'memoria total'
    AND m1.descricao = 'uso de ram kt';


CREATE VIEW VW_JANELA_PROCESSO AS
SELECT
    p.pid,
    p.nome_processo,
    v.data_hora,
    v.usado,
    CAST(p.uso_memoria AS DECIMAL(10, 2)) AS uso_memoria,
    p.status_abertura,
    v.id_maquina,
    p.fk_empresaP
FROM
    (
        SELECT
            FORMAT(m1.data_hora, 'yyyy-MM-dd HH:mm:ss') AS data_hora,
            ROUND(((1 - (m1.dado_coletado / m2.dado_coletado)) * 100), 2) AS usado,
            ROUND(((m1.dado_coletado / m2.dado_coletado) * 100), 2) AS livre,
            m2.dado_coletado AS total,
            c.nome_componente,
            ma.id_maquina,
            ma.hostname,
            e.razao_social
        FROM
            nocline.monitoramento m1
        JOIN
            nocline.monitoramento m2 ON m1.fk_maquina_monitoramento = m2.fk_maquina_monitoramento
        JOIN
            nocline.componente c ON m1.fk_componentes_monitoramento = c.id_componente
        JOIN
            nocline.maquina ma ON m1.fk_maquina_monitoramento = ma.id_maquina
        JOIN
            nocline.empresa e ON ma.fk_empresaM = e.id_empresa
        WHERE
            c.nome_componente = 'RAM'
            AND m2.descricao = 'memoria total'
            AND m1.descricao = 'uso de ram kt'
    ) v
INNER JOIN
    (
        SELECT
            pid,
            nome_processo,
            uso_cpu,
            uso_memoria,
            memoria_virtual,
            status_abertura,
            gravacao_disco,
            temp_execucao,
            fk_maquinaP,
            fk_empresaP,
            data_hora
        FROM
            processos
        WHERE
            data_hora IS NOT NULL
    ) p
ON
    v.data_hora = p.data_hora
ORDER BY
    v.data_hora, p.data_hora;
