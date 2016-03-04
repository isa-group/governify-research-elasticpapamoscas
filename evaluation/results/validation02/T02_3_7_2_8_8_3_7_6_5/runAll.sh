#Tests with: PapamoscasElastic
../runSingleTest.sh T02.01.00-2_8_8_3_7_6_5 [2,8,8,3,7,6,5] 90 PapamoscasElastic 760
../runSingleTest.sh T02.01.01-2_8_8_3_7_6_5 [2,8,8,3,7,6,5] 90 PapamoscasElastic 760
../runSingleTest.sh T02.01.02-2_8_8_3_7_6_5 [2,8,8,3,7,6,5] 90 PapamoscasElastic 760

#Tests with: PapamoscasElasticOnlyOneLevel
../runSingleTest.sh T02.01.00-2_8_8_3_7_6_5 [2,8,8,3,7,6,5] 90 PapamoscasElasticOnlyOneLevel 760
../runSingleTest.sh T02.01.01-2_8_8_3_7_6_5 [2,8,8,3,7,6,5] 90 PapamoscasElasticOnlyOneLevel 760
../runSingleTest.sh T02.01.02-2_8_8_3_7_6_5 [2,8,8,3,7,6,5] 90 PapamoscasElasticOnlyOneLevel 760

#Tests with: PapamoscasElasticOnlyOneLevelPreProvisioned
../runSingleTest.sh T02.01.00-2_8_8_3_7_6_5 [2,8,8,3,7,6,5] 90 PapamoscasElasticOnlyOneLevelPreProvisioned 760
../runSingleTest.sh T02.01.01-2_8_8_3_7_6_5 [2,8,8,3,7,6,5] 90 PapamoscasElasticOnlyOneLevelPreProvisioned 760
../runSingleTest.sh T02.01.02-2_8_8_3_7_6_5 [2,8,8,3,7,6,5] 90 PapamoscasElasticOnlyOneLevelPreProvisioned 760

node ../summaryAll.js T02
