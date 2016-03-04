#Tests with: PapamoscasElastic
../runSingleTest.sh T03.01.00-2_4_4_1_8_5_9 [2,4,4,1,8,5,9] 120 PapamoscasElastic 760
../runSingleTest.sh T03.01.01-2_4_4_1_8_5_9 [2,4,4,1,8,5,9] 120 PapamoscasElastic 760
../runSingleTest.sh T03.01.02-2_4_4_1_8_5_9 [2,4,4,1,8,5,9] 120 PapamoscasElastic 760

#Tests with: PapamoscasElasticOnlyOneLevel
../runSingleTest.sh T03.01.00-2_4_4_1_8_5_9 [2,4,4,1,8,5,9] 120 PapamoscasElasticOnlyOneLevel 760
../runSingleTest.sh T03.01.01-2_4_4_1_8_5_9 [2,4,4,1,8,5,9] 120 PapamoscasElasticOnlyOneLevel 760
../runSingleTest.sh T03.01.02-2_4_4_1_8_5_9 [2,4,4,1,8,5,9] 120 PapamoscasElasticOnlyOneLevel 760

#Tests with: PapamoscasElasticOnlyOneLevelPreProvisioned
../runSingleTest.sh T03.01.00-2_4_4_1_8_5_9 [2,4,4,1,8,5,9] 120 PapamoscasElasticOnlyOneLevelPreProvisioned 760
../runSingleTest.sh T03.01.01-2_4_4_1_8_5_9 [2,4,4,1,8,5,9] 120 PapamoscasElasticOnlyOneLevelPreProvisioned 760
../runSingleTest.sh T03.01.02-2_4_4_1_8_5_9 [2,4,4,1,8,5,9] 120 PapamoscasElasticOnlyOneLevelPreProvisioned 760

node ../summaryAll.js T03
