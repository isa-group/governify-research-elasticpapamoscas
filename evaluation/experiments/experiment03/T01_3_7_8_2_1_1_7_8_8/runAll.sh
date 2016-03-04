#Tests with: PapamoscasElastic
../runSingleTest.sh T01.01.00-8_2_1_1_7_8_8 [8,2,1,1,7,8,8] 120 PapamoscasElastic 760
../runSingleTest.sh T01.01.01-8_2_1_1_7_8_8 [8,2,1,1,7,8,8] 120 PapamoscasElastic 760
../runSingleTest.sh T01.01.02-8_2_1_1_7_8_8 [8,2,1,1,7,8,8] 120 PapamoscasElastic 760

#Tests with: PapamoscasElasticOnlyOneLevel
../runSingleTest.sh T01.01.00-8_2_1_1_7_8_8 [8,2,1,1,7,8,8] 120 PapamoscasElasticOnlyOneLevel 760
../runSingleTest.sh T01.01.01-8_2_1_1_7_8_8 [8,2,1,1,7,8,8] 120 PapamoscasElasticOnlyOneLevel 760
../runSingleTest.sh T01.01.02-8_2_1_1_7_8_8 [8,2,1,1,7,8,8] 120 PapamoscasElasticOnlyOneLevel 760

#Tests with: PapamoscasElasticOnlyOneLevelPreProvisioned
../runSingleTest.sh T01.01.00-8_2_1_1_7_8_8 [8,2,1,1,7,8,8] 120 PapamoscasElasticOnlyOneLevelPreProvisioned 760
../runSingleTest.sh T01.01.01-8_2_1_1_7_8_8 [8,2,1,1,7,8,8] 120 PapamoscasElasticOnlyOneLevelPreProvisioned 760
../runSingleTest.sh T01.01.02-8_2_1_1_7_8_8 [8,2,1,1,7,8,8] 120 PapamoscasElasticOnlyOneLevelPreProvisioned 760

node ../summaryAll.js T01
