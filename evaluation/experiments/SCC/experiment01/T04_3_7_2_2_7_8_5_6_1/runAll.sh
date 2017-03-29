#Tests with: PapamoscasElastic
../runSingleTest.sh T04.01.00-2_2_7_8_5_6_1 [2,2,7,8,5,6,1] 120 PapamoscasElastic 760
../runSingleTest.sh T04.01.01-2_2_7_8_5_6_1 [2,2,7,8,5,6,1] 120 PapamoscasElastic 760
../runSingleTest.sh T04.01.02-2_2_7_8_5_6_1 [2,2,7,8,5,6,1] 120 PapamoscasElastic 760

#Tests with: PapamoscasElasticOnlyOneLevel
../runSingleTest.sh T04.01.00-2_2_7_8_5_6_1 [2,2,7,8,5,6,1] 120 PapamoscasElasticOnlyOneLevel 760
../runSingleTest.sh T04.01.01-2_2_7_8_5_6_1 [2,2,7,8,5,6,1] 120 PapamoscasElasticOnlyOneLevel 760
../runSingleTest.sh T04.01.02-2_2_7_8_5_6_1 [2,2,7,8,5,6,1] 120 PapamoscasElasticOnlyOneLevel 760

#Tests with: PapamoscasElasticOnlyOneLevelPreProvisioned
../runSingleTest.sh T04.01.00-2_2_7_8_5_6_1 [2,2,7,8,5,6,1] 120 PapamoscasElasticOnlyOneLevelPreProvisioned 760
../runSingleTest.sh T04.01.01-2_2_7_8_5_6_1 [2,2,7,8,5,6,1] 120 PapamoscasElasticOnlyOneLevelPreProvisioned 760
../runSingleTest.sh T04.01.02-2_2_7_8_5_6_1 [2,2,7,8,5,6,1] 120 PapamoscasElasticOnlyOneLevelPreProvisioned 760

node ../summaryAll.js T04
