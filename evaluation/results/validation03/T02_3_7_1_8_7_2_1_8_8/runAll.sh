#Tests with: PapamoscasElastic
../runSingleTest.sh T02.01.00-1_8_7_2_1_8_8 [1,8,7,2,1,8,8] 120 PapamoscasElastic 760
../runSingleTest.sh T02.01.01-1_8_7_2_1_8_8 [1,8,7,2,1,8,8] 120 PapamoscasElastic 760
../runSingleTest.sh T02.01.02-1_8_7_2_1_8_8 [1,8,7,2,1,8,8] 120 PapamoscasElastic 760

#Tests with: PapamoscasElasticOnlyOneLevel
../runSingleTest.sh T02.01.00-1_8_7_2_1_8_8 [1,8,7,2,1,8,8] 120 PapamoscasElasticOnlyOneLevel 760
../runSingleTest.sh T02.01.01-1_8_7_2_1_8_8 [1,8,7,2,1,8,8] 120 PapamoscasElasticOnlyOneLevel 760
../runSingleTest.sh T02.01.02-1_8_7_2_1_8_8 [1,8,7,2,1,8,8] 120 PapamoscasElasticOnlyOneLevel 760

#Tests with: PapamoscasElasticOnlyOneLevelPreProvisioned
../runSingleTest.sh T02.01.00-1_8_7_2_1_8_8 [1,8,7,2,1,8,8] 120 PapamoscasElasticOnlyOneLevelPreProvisioned 760
../runSingleTest.sh T02.01.01-1_8_7_2_1_8_8 [1,8,7,2,1,8,8] 120 PapamoscasElasticOnlyOneLevelPreProvisioned 760
../runSingleTest.sh T02.01.02-1_8_7_2_1_8_8 [1,8,7,2,1,8,8] 120 PapamoscasElasticOnlyOneLevelPreProvisioned 760

node ../summaryAll.js T02
