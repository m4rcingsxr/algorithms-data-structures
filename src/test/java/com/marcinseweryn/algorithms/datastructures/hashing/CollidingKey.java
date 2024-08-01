package com.marcinseweryn.algorithms.datastructures.hashing;

class CollidingKey {
        private final String key;
        private final int hash;

        public CollidingKey(String key, int hash) {
            this.key = key;
            this.hash = hash;
        }

        @Override
        public int hashCode() {
            return hash; // Return a fixed hash to ensure collision
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CollidingKey that = (CollidingKey) o;
            return key.equals(that.key);
        }

        @Override
        public String toString() {
            return key;
        }
    }