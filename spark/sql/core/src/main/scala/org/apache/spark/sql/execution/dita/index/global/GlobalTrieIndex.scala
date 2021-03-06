/*
 *  Copyright 2017 by DITA Project
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.apache.spark.sql.execution.dita.index.global

import org.apache.spark.sql.catalyst.expressions.dita.common.shape.Shape
import org.apache.spark.sql.catalyst.expressions.dita.common.trajectory.{Trajectory, TrajectorySimilarity}
import org.apache.spark.sql.catalyst.expressions.dita.index.GlobalIndex
import org.apache.spark.sql.execution.dita.partition.global.GlobalTriePartitioner

case class GlobalTrieIndex(partitioner: GlobalTriePartitioner) extends GlobalIndex {
  def getPartitions(key: Trajectory, distanceFunction: TrajectorySimilarity, threshold: Double): List[Int] = {
    partitioner.getPartitions(key, distanceFunction, threshold, 0.0).map(_._1)
  }

  def getPartitions(key: Shape, threshold: Double): List[Int] = {
    partitioner.getPartitions(key, threshold)
  }

  def getPartitionsWithDistances(key: Trajectory, distanceFunction: TrajectorySimilarity, threshold: Double): List[(Int, Double)] = {
    partitioner.getPartitions(key, distanceFunction, threshold, 0.0)
  }
}