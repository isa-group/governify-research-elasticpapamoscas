#Tests with: PapamoscasElastic
../runSingleTest.sh T00.01.00-1_6_5_9_1_3_6 [1,6,5,9,1,3,6] 90 PapamoscasElastic 760
../runSingleTest.sh T00.01.01-1_6_5_9_1_3_6 [1,6,5,9,1,3,6] 90 PapamoscasElastic 760
../runSingleTest.sh T00.01.02-1_6_5_9_1_3_6 [1,6,5,9,1,3,6] 90 PapamoscasElastic 760

#Tests with: PapamoscasElasticOnlyOneLevel
../runSingleTest.sh T00.01.00-1_6_5_9_1_3_6 [1,6,5,9,1,3,6] 90 PapamoscasElasticOnlyOneLevel 760
../runSingleTest.sh T00.01.01-1_6_5_9_1_3_6 [1,6,5,9,1,3,6] 90 PapamoscasElasticOnlyOneLevel 760
../runSingleTest.sh T00.01.02-1_6_5_9_1_3_6 [1,6,5,9,1,3,6] 90 PapamoscasElasticOnlyOneLevel 760

#Tests with: PapamoscasElasticOnlyOneLevelPreProvisioned
../runSingleTest.sh T00.01.00-1_6_5_9_1_3_6 [1,6,5,9,1,3,6] 90 PapamoscasElasticOnlyOneLevelPreProvisioned 760
../runSingleTest.sh T00.01.01-1_6_5_9_1_3_6 [1,6,5,9,1,3,6] 90 PapamoscasElasticOnlyOneLevelPreProvisioned 760
../runSingleTest.sh T00.01.02-1_6_5_9_1_3_6 [1,6,5,9,1,3,6] 90 PapamoscasElasticOnlyOneLevelPreProvisioned 760

node ../summaryAll.js T00
