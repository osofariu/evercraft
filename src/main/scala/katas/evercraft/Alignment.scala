package katas.evercraft

sealed trait Alignment {}

object Alignment extends Alignment {

  object Evil extends Alignment {}
  object Neutral extends Alignment {}
  object Good extends Alignment {}
}
