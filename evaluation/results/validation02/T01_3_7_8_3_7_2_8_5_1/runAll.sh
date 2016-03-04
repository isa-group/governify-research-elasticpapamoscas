#Tests with: PapamoscasElastic
../runSingleTest.sh T01.01.00-8_3_7_2_8_5_1 [8,3,7,2,8,5,1] 120 PapamoscasElastic 760
../runSingleTest.sh T01.01.01-8_3_7_2_8_5_1 [8,3,7,2,8,5,1] 120 PapamoscasElastic 760
../runSingleTest.sh T01.01.02-8_3_7_2_8_5_1 [8,3,7,2,8,5,1] 120 PapamoscasElastic 760

#Tests with: PapamoscasElasticOnlyOneLevel
../runSingleTest.sh T01.01.00-8_3_7_2_8_5_1 [8,3,7,2,8,5,1] 120 PapamoscasElasticOnlyOneLevel 760
../runSingleTest.sh T01.01.01-8_3_7_2_8_5_1 [8,3,7,2,8,5,1] 120 PapamoscasElasticOnlyOneLevel 760
../runSingleTest.sh T01.01.02-8_3_7_2_8_5_1 [8,3,7,2,8,5,1] 120 PapamoscasElasticOnlyOneLevel 760

#Tests with: PapamoscasElasticOnlyOneLevelPreProvisioned
../runSingleTest.sh T01.01.00-8_3_7_2_8_5_1 [8,3,7,2,8,5,1] 120 PapamoscasElasticOnlyOneLevelPreProvisioned 760
../runSingleTest.sh T01.01.01-8_3_7_2_8_5_1 [8,3,7,2,8,5,1] 120 PapamoscasElasticOnlyOneLevelPreProvisioned 760
../runSingleTest.sh T01.01.02-8_3_7_2_8_5_1 [8,3,7,2,8,5,1] 120 PapamoscasElasticOnlyOneLevelPreProvisioned 760

node ../summaryAll.js T01
