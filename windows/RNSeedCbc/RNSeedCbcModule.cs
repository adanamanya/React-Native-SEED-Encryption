using ReactNative.Bridge;
using System;
using System.Collections.Generic;
using Windows.ApplicationModel.Core;
using Windows.UI.Core;

namespace Seed.Cbc.RNSeedCbc
{
    /// <summary>
    /// A module that allows JS to share data.
    /// </summary>
    class RNSeedCbcModule : NativeModuleBase
    {
        /// <summary>
        /// Instantiates the <see cref="RNSeedCbcModule"/>.
        /// </summary>
        internal RNSeedCbcModule()
        {

        }

        /// <summary>
        /// The name of the native module.
        /// </summary>
        public override string Name
        {
            get
            {
                return "RNSeedCbc";
            }
        }
    }
}
