# LUMIN Miner AI

Aplicação Android experimental de mineração e benchmarking, desenvolvida em Kotlin.

## Estado
Primeira versão: interface Android, benchmark SHA-256 local e controlo de execução. **Ainda não minera criptomoedas nem gera receitas.**

## Compilar APK
GitHub Actions > Build Android APK > Run workflow. Descarregar o artefacto `lumin-miner-debug` após uma compilação bem-sucedida.

## Segurança
Sem root, sem mineração escondida, sem chaves privadas. O benchmark pára quando o utilizador sai da aplicação. Evitar testes prolongados e interromper se o dispositivo aquecer.

## Próximas fases
Integrar motor PoW auditado e licenciado, ligação Stratum, validação de shares, carteira pública e medição de consumo/temperatura. Não apresentar benchmarks locais como mineração real.
