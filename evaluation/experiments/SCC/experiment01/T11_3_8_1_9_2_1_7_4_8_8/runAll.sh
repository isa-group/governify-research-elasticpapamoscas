#Tests with: PapamoscasElastic
../runSingleTest.sh T11.01.00-1_9_2_1_7_4_8_8 [1,9,2,1,7,4,8,8] 120 PapamoscasElastic 760
../runSingleTest.sh T11.01.01-1_9_2_1_7_4_8_8 [1,9,2,1,7,4,8,8] 120 PapamoscasElastic 760
../runSingleTest.sh T11.01.02-1_9_2_1_7_4_8_8 [1,9,2,1,7,4,8,8] 120 PapamoscasElastic 760

#Tests with: PapamoscasElasticOnlyOneLevel
../runSingleTest.sh T11.01.00-1_9_2_1_7_4_8_8 [1,9,2,1,7,4,8,8] 120 PapamoscasElasticOnlyOneLevel 760
../runSingleTest.sh T11.01.01-1_9_2_1_7_4_8_8 [1,9,2,1,7,4,8,8] 120 PapamoscasElasticOnlyOneLevel 760
../runSingleTest.sh T11.01.02-1_9_2_1_7_4_8_8 [1,9,2,1,7,4,8,8] 120 PapamoscasElasticOnlyOneLevel 760

#Tests with: PapamoscasElasticOnlyOneLevelPreProvisioned
../runSingleTest.sh T11.01.00-1_9_2_1_7_4_8_8 [1,9,2,1,7,4,8,8] 120 PapamoscasElasticOnlyOneLevelPreProvisioned 760
../runSingleTest.sh T11.01.01-1_9_2_1_7_4_8_8 [1,9,2,1,7,4,8,8] 120 PapamoscasElasticOnlyOneLevelPreProvisioned 760
../runSingleTest.sh T11.01.02-1_9_2_1_7_4_8_8 [1,9,2,1,7,4,8,8] 120 PapamoscasElasticOnlyOneLevelPreProvisioned 760

node ../summaryAll.js T11
