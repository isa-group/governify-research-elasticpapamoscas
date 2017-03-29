#Tests with: PapamoscasElastic
../runSingleTest.sh T00.01.00-8_2_7_1_8_1_1 [8,2,7,1,8,1,1] 120 PapamoscasElastic 760
../runSingleTest.sh T00.01.01-8_2_7_1_8_1_1 [8,2,7,1,8,1,1] 120 PapamoscasElastic 760
../runSingleTest.sh T00.01.02-8_2_7_1_8_1_1 [8,2,7,1,8,1,1] 120 PapamoscasElastic 760

#Tests with: PapamoscasElasticOnlyOneLevel
../runSingleTest.sh T00.01.00-8_2_7_1_8_1_1 [8,2,7,1,8,1,1] 120 PapamoscasElasticOnlyOneLevel 760
../runSingleTest.sh T00.01.01-8_2_7_1_8_1_1 [8,2,7,1,8,1,1] 120 PapamoscasElasticOnlyOneLevel 760
../runSingleTest.sh T00.01.02-8_2_7_1_8_1_1 [8,2,7,1,8,1,1] 120 PapamoscasElasticOnlyOneLevel 760

#Tests with: PapamoscasElasticOnlyOneLevelPreProvisioned
../runSingleTest.sh T00.01.00-8_2_7_1_8_1_1 [8,2,7,1,8,1,1] 120 PapamoscasElasticOnlyOneLevelPreProvisioned 760
../runSingleTest.sh T00.01.01-8_2_7_1_8_1_1 [8,2,7,1,8,1,1] 120 PapamoscasElasticOnlyOneLevelPreProvisioned 760
../runSingleTest.sh T00.01.02-8_2_7_1_8_1_1 [8,2,7,1,8,1,1] 120 PapamoscasElasticOnlyOneLevelPreProvisioned 760

node ../summaryAll.js T00
