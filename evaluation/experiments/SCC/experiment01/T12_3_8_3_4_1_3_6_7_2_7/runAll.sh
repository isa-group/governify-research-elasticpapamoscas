#Tests with: PapamoscasElastic
../runSingleTest.sh T12.01.00-3_4_1_3_6_7_2_7 [3,4,1,3,6,7,2,7] 120 PapamoscasElastic 760
../runSingleTest.sh T12.01.01-3_4_1_3_6_7_2_7 [3,4,1,3,6,7,2,7] 120 PapamoscasElastic 760
../runSingleTest.sh T12.01.02-3_4_1_3_6_7_2_7 [3,4,1,3,6,7,2,7] 120 PapamoscasElastic 760

#Tests with: PapamoscasElasticOnlyOneLevel
../runSingleTest.sh T12.01.00-3_4_1_3_6_7_2_7 [3,4,1,3,6,7,2,7] 120 PapamoscasElasticOnlyOneLevel 760
../runSingleTest.sh T12.01.01-3_4_1_3_6_7_2_7 [3,4,1,3,6,7,2,7] 120 PapamoscasElasticOnlyOneLevel 760
../runSingleTest.sh T12.01.02-3_4_1_3_6_7_2_7 [3,4,1,3,6,7,2,7] 120 PapamoscasElasticOnlyOneLevel 760

#Tests with: PapamoscasElasticOnlyOneLevelPreProvisioned
../runSingleTest.sh T12.01.00-3_4_1_3_6_7_2_7 [3,4,1,3,6,7,2,7] 120 PapamoscasElasticOnlyOneLevelPreProvisioned 760
../runSingleTest.sh T12.01.01-3_4_1_3_6_7_2_7 [3,4,1,3,6,7,2,7] 120 PapamoscasElasticOnlyOneLevelPreProvisioned 760
../runSingleTest.sh T12.01.02-3_4_1_3_6_7_2_7 [3,4,1,3,6,7,2,7] 120 PapamoscasElasticOnlyOneLevelPreProvisioned 760

node ../summaryAll.js T12
