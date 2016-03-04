#Tests with: PapamoscasElastic
../runSingleTest.sh T10.01.00-3_2_1_8_4_3_1_2 [3,2,1,8,4,3,1,2] 90 PapamoscasElastic 760
../runSingleTest.sh T10.01.01-3_2_1_8_4_3_1_2 [3,2,1,8,4,3,1,2] 90 PapamoscasElastic 760
../runSingleTest.sh T10.01.02-3_2_1_8_4_3_1_2 [3,2,1,8,4,3,1,2] 90 PapamoscasElastic 760

#Tests with: PapamoscasElasticOnlyOneLevel
../runSingleTest.sh T10.01.00-3_2_1_8_4_3_1_2 [3,2,1,8,4,3,1,2] 90 PapamoscasElasticOnlyOneLevel 760
../runSingleTest.sh T10.01.01-3_2_1_8_4_3_1_2 [3,2,1,8,4,3,1,2] 90 PapamoscasElasticOnlyOneLevel 760
../runSingleTest.sh T10.01.02-3_2_1_8_4_3_1_2 [3,2,1,8,4,3,1,2] 90 PapamoscasElasticOnlyOneLevel 760

#Tests with: PapamoscasElasticOnlyOneLevelPreProvisioned
../runSingleTest.sh T10.01.00-3_2_1_8_4_3_1_2 [3,2,1,8,4,3,1,2] 90 PapamoscasElasticOnlyOneLevelPreProvisioned 760
../runSingleTest.sh T10.01.01-3_2_1_8_4_3_1_2 [3,2,1,8,4,3,1,2] 90 PapamoscasElasticOnlyOneLevelPreProvisioned 760
../runSingleTest.sh T10.01.02-3_2_1_8_4_3_1_2 [3,2,1,8,4,3,1,2] 90 PapamoscasElasticOnlyOneLevelPreProvisioned 760

node ../summaryAll.js T10
