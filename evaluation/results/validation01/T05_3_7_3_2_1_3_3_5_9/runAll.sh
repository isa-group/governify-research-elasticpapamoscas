#Tests with: PapamoscasElastic
../runSingleTest.sh T05.01.00-3_2_1_3_3_5_9 [3,2,1,3,3,5,9] 120 PapamoscasElastic 760
../runSingleTest.sh T05.01.01-3_2_1_3_3_5_9 [3,2,1,3,3,5,9] 120 PapamoscasElastic 760
../runSingleTest.sh T05.01.02-3_2_1_3_3_5_9 [3,2,1,3,3,5,9] 120 PapamoscasElastic 760

#Tests with: PapamoscasElasticOnlyOneLevel
../runSingleTest.sh T05.01.00-3_2_1_3_3_5_9 [3,2,1,3,3,5,9] 120 PapamoscasElasticOnlyOneLevel 760
../runSingleTest.sh T05.01.01-3_2_1_3_3_5_9 [3,2,1,3,3,5,9] 120 PapamoscasElasticOnlyOneLevel 760
../runSingleTest.sh T05.01.02-3_2_1_3_3_5_9 [3,2,1,3,3,5,9] 120 PapamoscasElasticOnlyOneLevel 760

#Tests with: PapamoscasElasticOnlyOneLevelPreProvisioned
../runSingleTest.sh T05.01.00-3_2_1_3_3_5_9 [3,2,1,3,3,5,9] 120 PapamoscasElasticOnlyOneLevelPreProvisioned 760
../runSingleTest.sh T05.01.01-3_2_1_3_3_5_9 [3,2,1,3,3,5,9] 120 PapamoscasElasticOnlyOneLevelPreProvisioned 760
../runSingleTest.sh T05.01.02-3_2_1_3_3_5_9 [3,2,1,3,3,5,9] 120 PapamoscasElasticOnlyOneLevelPreProvisioned 760

node ../summaryAll.js T05
