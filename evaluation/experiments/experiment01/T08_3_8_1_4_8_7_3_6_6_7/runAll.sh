#Tests with: PapamoscasElastic
../runSingleTest.sh T08.01.00-1_4_8_7_3_6_6_7 [1,4,8,7,3,6,6,7] 90 PapamoscasElastic 760
../runSingleTest.sh T08.01.01-1_4_8_7_3_6_6_7 [1,4,8,7,3,6,6,7] 90 PapamoscasElastic 760
../runSingleTest.sh T08.01.02-1_4_8_7_3_6_6_7 [1,4,8,7,3,6,6,7] 90 PapamoscasElastic 760

#Tests with: PapamoscasElasticOnlyOneLevel
../runSingleTest.sh T08.01.00-1_4_8_7_3_6_6_7 [1,4,8,7,3,6,6,7] 90 PapamoscasElasticOnlyOneLevel 760
../runSingleTest.sh T08.01.01-1_4_8_7_3_6_6_7 [1,4,8,7,3,6,6,7] 90 PapamoscasElasticOnlyOneLevel 760
../runSingleTest.sh T08.01.02-1_4_8_7_3_6_6_7 [1,4,8,7,3,6,6,7] 90 PapamoscasElasticOnlyOneLevel 760

#Tests with: PapamoscasElasticOnlyOneLevelPreProvisioned
../runSingleTest.sh T08.01.00-1_4_8_7_3_6_6_7 [1,4,8,7,3,6,6,7] 90 PapamoscasElasticOnlyOneLevelPreProvisioned 760
../runSingleTest.sh T08.01.01-1_4_8_7_3_6_6_7 [1,4,8,7,3,6,6,7] 90 PapamoscasElasticOnlyOneLevelPreProvisioned 760
../runSingleTest.sh T08.01.02-1_4_8_7_3_6_6_7 [1,4,8,7,3,6,6,7] 90 PapamoscasElasticOnlyOneLevelPreProvisioned 760

node ../summaryAll.js T08
