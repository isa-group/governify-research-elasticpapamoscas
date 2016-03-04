#Tests with: PapamoscasElastic
../runSingleTest.sh T09.01.00-1_9_9_8_1_5_4_8 [1,9,9,8,1,5,4,8] 90 PapamoscasElastic 760
../runSingleTest.sh T09.01.01-1_9_9_8_1_5_4_8 [1,9,9,8,1,5,4,8] 90 PapamoscasElastic 760
../runSingleTest.sh T09.01.02-1_9_9_8_1_5_4_8 [1,9,9,8,1,5,4,8] 90 PapamoscasElastic 760

#Tests with: PapamoscasElasticOnlyOneLevel
../runSingleTest.sh T09.01.00-1_9_9_8_1_5_4_8 [1,9,9,8,1,5,4,8] 90 PapamoscasElasticOnlyOneLevel 760
../runSingleTest.sh T09.01.01-1_9_9_8_1_5_4_8 [1,9,9,8,1,5,4,8] 90 PapamoscasElasticOnlyOneLevel 760
../runSingleTest.sh T09.01.02-1_9_9_8_1_5_4_8 [1,9,9,8,1,5,4,8] 90 PapamoscasElasticOnlyOneLevel 760

#Tests with: PapamoscasElasticOnlyOneLevelPreProvisioned
../runSingleTest.sh T09.01.00-1_9_9_8_1_5_4_8 [1,9,9,8,1,5,4,8] 90 PapamoscasElasticOnlyOneLevelPreProvisioned 760
../runSingleTest.sh T09.01.01-1_9_9_8_1_5_4_8 [1,9,9,8,1,5,4,8] 90 PapamoscasElasticOnlyOneLevelPreProvisioned 760
../runSingleTest.sh T09.01.02-1_9_9_8_1_5_4_8 [1,9,9,8,1,5,4,8] 90 PapamoscasElasticOnlyOneLevelPreProvisioned 760

node ../summaryAll.js T09
